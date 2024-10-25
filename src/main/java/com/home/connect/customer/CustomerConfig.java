package com.home.connect.customer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CustomerConfig {
    @Bean
    public CustomerService customerService(CustomerRepository repository, PasswordEncoder encoder) {
        return new CustomerService(repository, encoder);
    }

    @Bean
    public CustomerDetailsService customerDetailsService(CustomerRepository repository) {
        return new CustomerDetailsService(repository);
    }

}