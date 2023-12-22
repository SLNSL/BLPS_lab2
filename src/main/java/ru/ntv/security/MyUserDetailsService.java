package ru.ntv.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ntv.entity.User;
import ru.ntv.repo.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository
                .findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
        final var role = user.getRole();

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                role.getPrivileges()
        );
    }
}