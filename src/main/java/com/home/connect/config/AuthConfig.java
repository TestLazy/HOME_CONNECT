package com.home.connect.config;

import com.home.connect.auth.AuthService;
import com.home.connect.customer.CustomerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthConfig {
    @Bean
    public AuthService authService(
            JWTConfig jwtService,
            PasswordEncoder encoder,
            CustomerRepository repository,
            AuthenticationManager authentication) {
        return new AuthService(
                jwtService,
                encoder,
                repository,
                authentication);
    }
}