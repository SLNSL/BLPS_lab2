package ru.ntv.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ru.ntv.etc.DatabaseRole;
import ru.ntv.security.JwtAuthenticationFilter;
import ru.ntv.security.JwtAuthenticationPoint;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableTransactionManagement
public class SecurityConfig{
    private final JwtAuthenticationPoint unauthorizedHandler;
    private final JwtAuthenticationFilter authenticationFilter;

    public SecurityConfig(JwtAuthenticationPoint unauthorizedHandler, JwtAuthenticationFilter authenticationFilter) {
        this.unauthorizedHandler = unauthorizedHandler;
        this.authenticationFilter =authenticationFilter;
    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("localhost"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .cors()
                .and()
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)  //если пользователь не зарегестрирован,то он обрабатывается тут
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
//                .authorizeHttpRequests((auth) -> auth
//                        .requestMatchers("/auth/**", "/common/**", "/articles/**", "/themes/**")
//                        .permitAll()
//                        .requestMatchers("/admin/**")
//                        .hasAuthority(DatabaseRole.ROLE_ADMIN.name())
//                        .requestMatchers("/user/**")
//                        .hasAuthority(DatabaseRole.ROLE_CLIENT.name())
//                        .requestMatchers("*")
//                        .denyAll()
//                )
                .build();
    }
}