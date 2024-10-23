package com.home.connect.customer;

import com.home.connect.system.exceptions.EmailNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomerDetailsService implements UserDetailsService {
    private final CustomerRepository repository;

    public CustomerDetailsService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new CustomerDetails(repository
                .findByUsername(username)
                .orElseThrow(EmailNotFoundException::new));
    }
}