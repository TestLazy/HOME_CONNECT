package com.home.connect.config;

import com.home.connect.auth.JWTService;
import com.home.connect.customer.CustomerPermission;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Value("${jwt.public.key}")
    private RSAPublicKey publicKey;

    @Value("${jwt.private.key}")
    private RSAPrivateKey privateKey;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(request -> request

                        .requestMatchers(HttpMethod.POST, "/auth/sign-in", "/auth/sign-up")
                        .permitAll()

                        .requestMatchers(HttpMethod.GET, "/customers/user/**")
                        .hasAuthority("SCOPE_" + CustomerPermission.ROLE_USER)

                        .requestMatchers(HttpMethod.PUT, "/customers/user/**")
                        .hasAuthority("SCOPE_" + CustomerPermission.ROLE_USER)

                        .requestMatchers(HttpMethod.GET, "/customers/admin/**")
                        .hasAuthority("SCOPE_" + CustomerPermission.ROLE_ADMIN)

                        .requestMatchers(HttpMethod.PUT, "/customers/user/**")
                        .hasAuthority("SCOPE_" + CustomerPermission.ROLE_ADMIN)

                        .requestMatchers(HttpMethod.DELETE, "/customers/admin/**")
                        .hasAuthority("SCOPE_" + CustomerPermission.ROLE_ADMIN)

                        .anyRequest()
                        .authenticated())

                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults()))

                .build();
    }

    @Bean
    public JwtEncoder jwtEncoder() {
        return new NimbusJwtEncoder(
                new ImmutableJWKSet<>(new JWKSet(new RSAKey
                        .Builder(publicKey)
                        .privateKey(privateKey)
                        .build())));
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JWTService jwtCryptService() {
        return new JWTService(jwtEncoder());
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(publicKey).build();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}