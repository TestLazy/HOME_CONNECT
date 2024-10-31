package com.home.connect.property;

import com.home.connect.customer.Customer;
import com.home.connect.customer.CustomerRepository;
import com.home.connect.system.exceptions.EntityNotFoundException;
import com.home.connect.system.exceptions.UnauthorizedActionException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public class PropertyService {
    private final PropertyRepository repository;
    private final CustomerRepository customerRepository;

    public PropertyService(PropertyRepository repository, CustomerRepository customerRepository) {
        this.repository = repository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public void save(PropertyRequest entity) {
        repository.save(new Property(property -> {
            property.setValue(entity.value());
            property.setDescription(entity.description());
        }));
    }

    @Transactional
    public void saveInCustomer(Integer idCustomer, Integer idProperty) {
        String authentication = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        Property propertyExisting = repository
                .findById(idProperty)
                .orElseThrow(() -> new EntityNotFoundException("Propriedade não encontrada!"));

        Customer customerExisting = customerRepository
                .findById(idCustomer)
                .orElseThrow(EntityNotFoundException::new);

        if (customerExisting.getUsername().equalsIgnoreCase(authentication)) {
            propertyExisting.setCustomer(customerExisting);
            customerExisting.getProperties().add(propertyExisting);

            repository.save(propertyExisting);
            customerRepository.save(customerExisting);
        }

        throw new UnauthorizedActionException();
    }

    @Transactional(readOnly = true)
    public Property findById(Integer idProperty) {
        return repository
                .findById(idProperty)
                .orElseThrow(() -> new EntityNotFoundException("Propriedade não encontrada!"));
    }

    @Transactional(readOnly = true)
    public Page<Property> findAllPaged(Pageable pageable) {
        return repository.findAllByOrderByValueAsc(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Property> searchProperty(BigDecimal value, Pageable pageable) {
        return repository.findAllByValue(value, pageable);
    }

    @Transactional
    public void updateById(Integer idProperty, PropertyRequest request) {
        Property propertyExisting = repository
                .findById(idProperty)
                .orElseThrow(() -> new EntityNotFoundException("Propriedade não encontrada!"));

        Property updatedEntity = new Property();

        updatedEntity.setId(propertyExisting.getId());
        updatedEntity.setDescription(request.description());
        updatedEntity.setValue(request.value());
        updatedEntity.setCustomer(propertyExisting.getCustomer());

        repository.save(updatedEntity);
    }

    @Transactional
    public void deleteById(Integer id) {
        Property propertyExisting = repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Propriedade não encontrada!"));

        repository.deleteById(propertyExisting.getId());
    }
}