package com.home.connect.auth;

import com.home.connect.customer.Customer;
import com.home.connect.customer.CustomerPermission;
import com.home.connect.customer.CustomerRepository;
import com.home.connect.system.dtos.SignInDTO;
import com.home.connect.system.dtos.SignUpDTO;
import com.home.connect.system.dtos.TokenDTO;
import com.home.connect.system.exceptions.EmailNotFoundException;
import com.home.connect.system.exceptions.InvalidPasswordException;
import com.home.connect.system.exceptions.PersonalNumberAlreadyExistsException;
import com.home.connect.system.exceptions.UsernameAlreadyExistsException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthService {
    private final JWTService service;
    private final PasswordEncoder encoder;

    private final CustomerRepository repository;
    private final AuthenticationManager authentication;

    public AuthService(
            JWTService service,
            PasswordEncoder encoder,
            CustomerRepository repository,
            AuthenticationManager authentication) {
        this.service = service;
        this.encoder = encoder;
        this.repository = repository;
        this.authentication = authentication;
    }

    public void signUp(SignUpDTO dto) {
        if (repository.existsByUsername(dto.username()))
            throw new UsernameAlreadyExistsException();

        if (repository.existsByPersonalNumber(dto.personalNumber()))
            throw new PersonalNumberAlreadyExistsException();

        repository.save(new Customer(entity -> {
            entity.setUsername(dto.username());
            entity.setPassword(encoder.encode(dto.password()));
            entity.setFullName(dto.fullName());
            entity.setPersonalNumber(dto.personalNumber());
            entity.setPermission(CustomerPermission.ROLE_USER);
        }));
    }

    public TokenDTO signIn(SignInDTO dto) {
        Customer entityExisting = repository
                .findByUsername(dto.username())
                .orElseThrow(EmailNotFoundException::new);

        if (entityExisting.getPermission().getCode() == 1)
            if (!encoder.matches(dto.password(), entityExisting.getPassword()))
                throw new InvalidPasswordException();

        return new TokenDTO(service.generateToken(authentication.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.username(),
                        dto.password()))));
    }
}