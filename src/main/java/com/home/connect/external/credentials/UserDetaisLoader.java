package com.home.connect.external.credentials;

import com.home.connect.core.domain.exceptions.EmailNotFoundException;
import com.home.connect.core.domain.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetaisLoader implements UserDetailsService {
    private final UserRepository repository;

    public UserDetaisLoader(UserRepository repository) {this.repository = repository;}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserDetailsCustom(repository
                .findByUsername(username)
                .orElseThrow(EmailNotFoundException::new));
    }
}