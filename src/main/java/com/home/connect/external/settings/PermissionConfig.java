package com.home.connect.external.settings;

import com.home.connect.core.domain.repositories.UserRepository;
import com.home.connect.external.services.JWTService;
import com.home.connect.external.services.PermissionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PermissionConfig {
    @Bean
    public PermissionService permissionService(
            JWTService jwtService,
            PasswordEncoder encoder,
            UserRepository repository,
            AuthenticationManager authentication) {
        return new PermissionService(
                jwtService,
                encoder,
                repository,
                authentication);
    }
}