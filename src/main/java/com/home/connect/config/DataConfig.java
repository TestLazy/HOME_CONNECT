package com.home.connect.config;

import com.home.connect.customer.Customer;
import com.home.connect.customer.CustomerPermission;
import com.home.connect.customer.CustomerRepository;
import com.home.connect.property.Property;
import com.home.connect.property.PropertyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataConfig implements CommandLineRunner {
    private final PasswordEncoder encoder;
    private final CustomerRepository repository;
    private final PropertyRepository propertyRepository;

    public DataConfig(PasswordEncoder encoder, CustomerRepository repository, PropertyRepository propertyRepository) {
        this.encoder = encoder;
        this.repository = repository;
        this.propertyRepository = propertyRepository;
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

        propertyRepository
                .findByDescription("PropertyTest")
                .orElseGet(() -> {
                    Property entity = new Property();
                    entity.setDescription("PropertyTest");
                    entity.setValue(BigDecimal.valueOf(125000));
                    return propertyRepository.save(entity);
                });
    }
}