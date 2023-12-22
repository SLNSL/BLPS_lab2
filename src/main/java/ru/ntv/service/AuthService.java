package ru.ntv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ntv.dto.request.auth.NewUser;
import ru.ntv.dto.request.auth.OldUser;
import ru.ntv.dto.response.auth.AuthResponse;
import ru.ntv.entity.User;
import ru.ntv.etc.DatabaseRole;
import ru.ntv.repo.RoleRepository;
import ru.ntv.repo.UserRepository;
import ru.ntv.security.JwtTokenProvider;

@Service
public class AuthService {

    final AuthenticationManager authenticationManager;

    final JwtTokenProvider jwtUtils;

    final UserRepository userRepository;

    final PasswordEncoder encoder;

    final RoleRepository roleRepository;

    public AuthService(AuthenticationManager authenticationManager, JwtTokenProvider jwtUtils, UserRepository userRepository, PasswordEncoder encoder, RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
    }

    public AuthResponse signIn(OldUser user) throws BadCredentialsException {
        final var response = new AuthResponse();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final var jwt = jwtUtils.generateJWTFromAuthentication(authentication);
        final var refreshToken = jwtUtils.generateRefreshTokenFromAuthentication(authentication);
        response.setJwt("Bearer " + jwt);
        response.setRefreshToken(refreshToken);

        return response;
    }

    public ResponseEntity<AuthResponse> signUp(NewUser newUser) {
        final var response = new AuthResponse();

        if (userRepository.existsByLogin(newUser.getUsername())) {
            response.setErrorMessage("This login is already taken");
            return ResponseEntity
                    .badRequest()
                    .body(response);
        }


        // Create new user's account
        final var user = new User();
        user.setLogin(newUser.getUsername());
        user.setPassword(encoder.encode(newUser.getPassword()));
        user.setRole(
                roleRepository.findRoleByName(
                        DatabaseRole.ROLE_CLIENT.name()
                )
        );
        userRepository.save(user);


        // Sign in
        final var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(newUser.getUsername(), newUser.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final var jwt = jwtUtils.generateJWTFromAuthentication(authentication);
        final var refreshToken = jwtUtils.generateRefreshTokenFromAuthentication(authentication);
        response.setJwt("Bearer " + jwt);
        response.setRefreshToken(refreshToken);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public AuthResponse refreshToken(String jwt) {
        final var response = new AuthResponse();

        if (!jwtUtils.validateRefreshToken(jwt)) {
            response.setErrorMessage("Invalid or expired refresh token");
            return response;
        }

        final var userLogin = jwtUtils.getUserLoginFromToken(jwt);
        response.setJwt("Bearer " + jwtUtils.generateJWT(userLogin));
        response.setRefreshToken(jwtUtils.generateRefreshToken(userLogin));

        return response;
    }

    public ResponseEntity<AuthResponse> createJournalist(NewUser journalist) {
        final var response = signUp(journalist);

        if (!response.getStatusCode().is2xxSuccessful()) {
            return response;
        }

        userRepository.findByLogin(journalist.getUsername()).get().setRole(
                roleRepository.findRoleByName(
                        DatabaseRole.ROLE_JOURNALIST.name()
                )
        );

        return response;
    }
}