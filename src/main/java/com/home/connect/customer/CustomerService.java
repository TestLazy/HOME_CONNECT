package com.home.connect.customer;

import com.home.connect.system.dtos.SignUpDTO;
import com.home.connect.system.exceptions.PersonalNumberAlreadyExistsException;
import com.home.connect.system.exceptions.UnauthorizedActionException;
import com.home.connect.system.exceptions.UsernameAlreadyExistsException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

public class CustomerService {
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Customer findById(Integer id) {
        String authentication = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        Customer entity = repository
                .findById(id)
                .orElseThrow(UnauthorizedActionException::new);

        if (entity.getUsername().equalsIgnoreCase(authentication))
            return entity;

        throw new UnauthorizedActionException();
    }

    @Transactional
    public void updateById(Integer id, SignUpDTO newEntity) {
        Customer existingEntity = findById(id);

        if (repository.existsByUsername(newEntity.username()))
            throw new UsernameAlreadyExistsException();

        if (repository.existsByPersonalNumber(newEntity.personalNumber()))
            throw new PersonalNumberAlreadyExistsException();

        Customer updatedEntity = new Customer();

        updatedEntity.setId(existingEntity.getId());
        updatedEntity.setUsername(newEntity.username());
        updatedEntity.setPassword(newEntity.password());
        updatedEntity.setFullName(newEntity.fullName());
        updatedEntity.setPersonalNumber(existingEntity.getPersonalNumber());
        updatedEntity.setPermission(existingEntity.getPermission());

        repository.save(updatedEntity);
    }

    @Transactional
    public void deleteById(Integer id) {
        repository.deleteById(findById(id).getId());
    }

    @Transactional(readOnly = true)
    public Page<Customer> findAllPaged(Integer page, Integer size) {
        return repository.findAllByOrderByUsernameAsc(PageRequest.of(page, size));
    }
}