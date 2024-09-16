package com.home.connect.external.services;

import com.home.connect.core.domain.entities.User;
import com.home.connect.core.domain.exceptions.EmailNotFoundException;
import com.home.connect.core.domain.exceptions.InvalidPasswordException;
import com.home.connect.core.domain.exceptions.PersonalNumberAlreadyExistsException;
import com.home.connect.core.domain.exceptions.UsernameAlreadyExistsException;
import com.home.connect.core.domain.repositories.UserRepository;
import com.home.connect.core.security.Permission;
import com.home.connect.intermediate.dtos.SignInDTO;
import com.home.connect.intermediate.dtos.SignUpDTO;
import com.home.connect.intermediate.dtos.TokenDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PermissionService {
    private final JWTService service;
    private final PasswordEncoder encoder;

    private final UserRepository repository;
    private final AuthenticationManager authentication;

    public PermissionService(
            JWTService service,
            PasswordEncoder encoder,
            UserRepository repository,
            AuthenticationManager authentication) {
        this.service = service;
        this.encoder = encoder;
        this.repository = repository;
        this.authentication = authentication;
    }

    public void signUp(SignUpDTO dto) {
        if (checkIfUsernameExists(dto.username()))
            throw new UsernameAlreadyExistsException();

        if (checkIfPersonalNumberExists(dto.personalNumber()))
            throw new PersonalNumberAlreadyExistsException();

        repository.save(new User(entity -> {
            entity.setUsername(dto.username());
            entity.setPassword(encoder.encode(dto.password()));
            entity.setFullName(dto.fullName());
            entity.setPersonalNumber(dto.personalNumber());
            entity.setPermission(Permission.valueOf(dto.permission()));
        }));
    }

    public TokenDTO signIn(SignInDTO dto) {
        isPasswordMatch(dto);

        return new TokenDTO(service.generateToken(authentication.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.username(),
                        dto.password()))));
    }

    private void isPasswordMatch(SignInDTO dto) {
        var entityExisting = repository
                .findByUsername(dto.username())
                .orElseThrow(EmailNotFoundException::new);

        if (!encoder.matches(dto.password(), entityExisting.getPassword()))
            throw new InvalidPasswordException();
    }

    private boolean checkIfUsernameExists(String username) {return repository.existsByUsername(username);}

    private boolean checkIfPersonalNumberExists(String personalNumber) {return repository.existsByPersonalNumber(personalNumber);}
}