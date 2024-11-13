package com.home.connect.customer;

import com.home.connect.system.exceptions.EntityNotFoundException;
import com.home.connect.system.exceptions.UnauthorizedActionException;
import com.home.connect.system.exceptions.UsernameAlreadyExistsException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

public class CustomerService {
    private final CustomerRepository repository;
    private final PasswordEncoder encoder;

    public CustomerService(CustomerRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Transactional(readOnly = true)
    public Customer findById(Integer id) {
        String authentication = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        Customer entity = repository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);

        if (entity.getUsername().equalsIgnoreCase(authentication))
            return entity;

        throw new UnauthorizedActionException();
    }

    @Transactional
    public void updateById(Integer id, CustomerSignUp newEntity) {
        Customer existingEntity = repository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);

        if (repository.existsByUsername(newEntity.username()))
            throw new UsernameAlreadyExistsException();

        if (hasAdminPermission(existingEntity))
            throw new UnauthorizedActionException();

        Customer updatedEntity = new Customer();

        updatedEntity.setId(existingEntity.getId());
        updatedEntity.setUsername(newEntity.username());
        updatedEntity.setPassword(encoder.encode(newEntity.password()));
        updatedEntity.setFullName(newEntity.fullName());
        updatedEntity.setPersonalNumber(existingEntity.getPersonalNumber());
        updatedEntity.setPermission(existingEntity.getPermission());

        repository.save(updatedEntity);
    }

    @Transactional
    public void updateById(Integer id, CustomerUsername newEntity) {
        Customer existingEntity = repository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);

        if (repository.existsByUsername(newEntity.username()))
            throw new UsernameAlreadyExistsException();

        if (hasAdminPermission(existingEntity))
            throw new UnauthorizedActionException();

        Customer updatedEntity = new Customer();

        updatedEntity.setId(existingEntity.getId());
        updatedEntity.setUsername(newEntity.username());
        updatedEntity.setPassword(existingEntity.getPassword());
        updatedEntity.setFullName(existingEntity.getFullName());
        updatedEntity.setPersonalNumber(existingEntity.getPersonalNumber());
        updatedEntity.setPermission(existingEntity.getPermission());

        repository.save(updatedEntity);
    }

    @Transactional
    public void updateById(Integer id, CustomerPassword newEntity) {
        Customer existingEntity = repository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);

        if (hasAdminPermission(existingEntity))
            throw new UnauthorizedActionException();

        Customer updatedEntity = new Customer();

        updatedEntity.setId(existingEntity.getId());
        updatedEntity.setUsername(existingEntity.getUsername());
        updatedEntity.setPassword(encoder.encode(newEntity.password()));
        updatedEntity.setFullName(existingEntity.getFullName());
        updatedEntity.setPersonalNumber(existingEntity.getPersonalNumber());
        updatedEntity.setPermission(existingEntity.getPermission());

        repository.save(updatedEntity);
    }

    @Transactional
    public void updateById(Integer id, CustomerFullName newEntity) {
        Customer existingEntity = repository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);

        if (hasAdminPermission(existingEntity))
            throw new UnauthorizedActionException();

        Customer updatedEntity = new Customer();

        updatedEntity.setId(existingEntity.getId());
        updatedEntity.setUsername(existingEntity.getUsername());
        updatedEntity.setPassword(existingEntity.getPassword());
        updatedEntity.setFullName(newEntity.fullName());
        updatedEntity.setPersonalNumber(existingEntity.getPersonalNumber());
        updatedEntity.setPermission(existingEntity.getPermission());

        repository.save(updatedEntity);
    }

    @Transactional
    public void deleteById(Integer id) {
        Customer existingEntity = repository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);

        if (hasAdminPermission(existingEntity))
            throw new UnauthorizedActionException();

        repository.deleteById(existingEntity.getId());
    }

    @Transactional(readOnly = true)
    public Page<Customer> findAllPaged(Pageable pageable) {
        return repository.findAllByOrderByUsernameAsc(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Customer> searchUsers(String username, Pageable pageable) {
        return repository.findByUsernameContainingIgnoreCase(username, pageable);
    }

    private boolean hasAdminPermission(Customer entity) {
        return entity.getPermission() == CustomerPermission.ROLE_ADMIN;
    }
}