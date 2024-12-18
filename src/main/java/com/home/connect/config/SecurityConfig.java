package com.home.connect.config;

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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.List;

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

                        .requestMatchers(HttpMethod.GET, "/properties/all/**")
                        .permitAll()

                        .requestMatchers(HttpMethod.GET, "/customers/user/**")
                        .hasAuthority("SCOPE_" + CustomerPermission.ROLE_USER)

                        .requestMatchers(HttpMethod.GET, "/customers/admin/**")
                        .hasAuthority("SCOPE_" + CustomerPermission.ROLE_ADMIN)

                        .requestMatchers(HttpMethod.PUT, "/customers/admin/**")
                        .hasAuthority("SCOPE_" + CustomerPermission.ROLE_ADMIN)

                        .requestMatchers(HttpMethod.DELETE, "/customers/admin/**")
                        .hasAuthority("SCOPE_" + CustomerPermission.ROLE_ADMIN)

                        .requestMatchers(HttpMethod.POST, "/properties/admin/**")
                        .hasAuthority("SCOPE_" + CustomerPermission.ROLE_ADMIN)

                        .requestMatchers(HttpMethod.GET, "/properties/admin/**")
                        .hasAuthority("SCOPE_" + CustomerPermission.ROLE_ADMIN)

                        .requestMatchers(HttpMethod.GET, "/properties/user/**")
                        .hasAuthority("SCOPE_" + CustomerPermission.ROLE_USER)

                        .requestMatchers(HttpMethod.PUT, "/properties/admin/**")
                        .hasAuthority("SCOPE_" + CustomerPermission.ROLE_ADMIN)

                        .requestMatchers(HttpMethod.DELETE, "/properties/admin/**")
                        .hasAuthority("SCOPE_" + CustomerPermission.ROLE_ADMIN)

                        .anyRequest()
                        .authenticated())

                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults()))

                .cors(c -> c.configurationSource(corsConfigurationSource()))
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
    public JWTConfig jwtCryptService() {
        return new JWTConfig(jwtEncoder());
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(publicKey).build();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}