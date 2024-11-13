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
    public void updateById(Integer id, CustomerDTO newEntity) {
        Customer existingEntity = repository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);

        if (newEntity.username() != null &&
                !existingEntity.getUsername().equals(newEntity.username())) {
            boolean usernameExists = repository.existsByUsername(newEntity.username());

            if (usernameExists) {
                Customer conflictingUser = repository
                        .findByUsername(newEntity.username())
                        .orElseThrow(UsernameAlreadyExistsException::new);

                if (!conflictingUser.getId().equals(id)) {
                    throw new UsernameAlreadyExistsException();
                }
            }
        }
        if (hasAdminPermission(existingEntity))
            throw new UnauthorizedActionException();

        if (newEntity.username() != null)
            existingEntity.setUsername(newEntity.username());

        if (newEntity.password() != null)
            existingEntity.setPassword(encoder.encode(newEntity.password()));

        if (newEntity.fullName() != null)
            existingEntity.setFullName(newEntity.fullName());

        repository.save(existingEntity);
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