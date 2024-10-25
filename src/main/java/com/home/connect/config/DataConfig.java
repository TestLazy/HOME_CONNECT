package com.home.connect.config;

import com.home.connect.customer.Customer;
import com.home.connect.customer.CustomerPermission;
import com.home.connect.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataConfig implements CommandLineRunner {
    private final PasswordEncoder encoder;
    private final CustomerRepository repository;

    public DataConfig(PasswordEncoder encoder, CustomerRepository repository) {
        this.encoder = encoder;
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        repository
                .findByUsername("Admin")
                .orElseGet(() -> {
                    Customer entity = new Customer();
                    entity.setFullName("Admin");
                    entity.setUsername("Admin");
                    entity.setPassword(encoder.encode("Admin"));
                    entity.setPersonalNumber("000-000-000");
                    entity.setPermission(CustomerPermission.ROLE_ADMIN);
                    return repository.save(entity);
                });

        repository
                .findByUsername("User")
                .orElseGet(() -> {
                    Customer entity = new Customer();
                    entity.setFullName("User");
                    entity.setUsername("User");
                    entity.setPassword(encoder.encode("User"));
                    entity.setPersonalNumber("111-111-111");
                    entity.setPermission(CustomerPermission.ROLE_USER);
                    return repository.save(entity);
                });
    }
}