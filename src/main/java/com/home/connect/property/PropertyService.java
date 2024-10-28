package com.home.connect.property;

import com.home.connect.customer.Customer;
import com.home.connect.customer.CustomerRepository;
import com.home.connect.system.exceptions.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class PropertyService {
    private final PropertyRepository repository;
    private final CustomerRepository customerRepository;

    public PropertyService(PropertyRepository repository, CustomerRepository customerRepository) {
        this.repository = repository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public void save(Property entity) {
        repository.save(entity);
    }

    @Transactional
    public void saveInCustomer(Integer idCustomer, Integer idProperty) {
        Property propertyExisting = repository
                .findById(idProperty)
                .orElseThrow(() -> new RuntimeException("dsa"));

        Customer customerExisting = customerRepository
                .findById(idCustomer)
                .orElseThrow(EntityNotFoundException::new);

        propertyExisting.setCustomer(customerExisting);
        // customerExisting.getProperties().add(propertyExisting);

        repository.save(propertyExisting);
        customerRepository.save(customerExisting);
    }
}