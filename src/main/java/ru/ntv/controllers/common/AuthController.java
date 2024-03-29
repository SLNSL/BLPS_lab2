package ru.ntv.controllers.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ntv.dto.request.auth.NewUser;
import ru.ntv.dto.request.auth.OldUser;
import ru.ntv.dto.response.auth.AuthResponse;
import ru.ntv.service.AuthService;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
@Validated
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("sign-in")
    ResponseEntity<AuthResponse> signIn(@Valid @RequestBody OldUser user) throws BadCredentialsException {
        return ResponseEntity.ok(authService.signIn(user));
    }

    @PostMapping("sign-up")
    ResponseEntity<AuthResponse> signUp(@Valid @RequestBody NewUser newUser) {
        return authService.signUp(newUser);
    }

    @PostMapping("refresh_token")
    ResponseEntity<AuthResponse> refreshToken(@RequestParam String jwt) {
        return ResponseEntity.ok(authService.refreshToken(jwt));
    }
}