package com.home.connect.customer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {
    @Bean
    public CustomerService customerService(CustomerRepository repository) {
        return new CustomerService(repository);
    }

    @Bean
    public CustomerDetailsService customerDetailsService(CustomerRepository repository) {
        return new CustomerDetailsService(repository);
    }

}