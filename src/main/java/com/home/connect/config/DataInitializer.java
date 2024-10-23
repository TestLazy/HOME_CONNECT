package com.home.connect.config;

import com.home.connect.customer.Customer;
import com.home.connect.customer.CustomerPermission;
import com.home.connect.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final PasswordEncoder encoder;
    private final CustomerRepository repository;

    public DataInitializer(PasswordEncoder encoder, CustomerRepository repository) {
        this.encoder = encoder;
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        repository
                .findByUsername("Connection")
                .orElseGet(() -> {
                    Customer entity = new Customer();
                    entity.setFullName("Connection");
                    entity.setUsername("Connection");
                    entity.setPassword(encoder.encode("Connection"));
                    entity.setPersonalNumber("Connection");
                    entity.setPermission(CustomerPermission.ROLE_ADMIN);
                    return repository.save(entity);
                });
    }
}