package com.home.connect.property;

import com.home.connect.customer.CustomerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertyConfig {
    @Bean
    public PropertyService propertyService(CustomerRepository customerRepository, PropertyRepository repository) {
        return new PropertyService(repository, customerRepository);
    }
}