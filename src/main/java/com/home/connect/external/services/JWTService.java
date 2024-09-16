package com.home.connect.external.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import java.time.Instant;
import java.util.stream.Collectors;

public class JWTService {
    private final JwtEncoder service;

    public JWTService(JwtEncoder service) {this.service = service;}

    public String generateToken(Authentication authentication) {
        var scope = authentication
                .getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("home-connect")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(36000L))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();

        return getToken(claims);
    }

    private String getToken(JwtClaimsSet claims) {return service.encode(JwtEncoderParameters.from(claims)).getTokenValue();}
}