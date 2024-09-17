package com.home.connect.external.gateways;

import com.home.connect.core.domain.entities.User;
import com.home.connect.core.domain.exceptions.EntityNotFoundException;
import com.home.connect.core.domain.exceptions.UnauthorizedActionException;
import com.home.connect.core.domain.exceptions.UsernameAlreadyExistsException;
import com.home.connect.core.domain.repositories.UserRepository;
import com.home.connect.core.security.Permission;
import com.home.connect.intermediate.dtos.SignDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {this.repository = repository;}

    @Transactional(readOnly = true)
    public User findById(Integer id) {
        var authenticatedUser = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        var user = repository.findByUsername(authenticatedUser)
                .get();

        var entityExisting = repository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);

        if (user.getId().equals(entityExisting.getId()) ||
                entityExisting.getPermission().equals(Permission.ROLE_USER))
            return entityExisting;

        throw new UnauthorizedActionException();
    }

    @Transactional
    public void updateById(Integer id, SignDTO newEntity) {
        var entityExisting = findById(id);

        if (checkIfUsernameExists(newEntity.username()))
            throw new UsernameAlreadyExistsException();

        repository.save(new User(entity -> {
            entity.setId(entityExisting.getId());
            entity.setUsername(newEntity.username());
            entity.setPassword(newEntity.password());
            entity.setFullName(newEntity.fullName());
            entity.setPermission(entityExisting.getPermission());
            entity.setPersonalNumber(entityExisting.getPersonalNumber());
        }));
    }

    @Transactional
    public void deleteById(Integer id) {
        var authenticatedUser = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        var user = repository
                .findByUsername(authenticatedUser)
                .get();

        var entityExisting = repository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);

        if (entityExisting.getPermission().equals(Permission.ROLE_SUPPORT))
            throw new UnauthorizedActionException();

        repository.deleteById(entityExisting.getId());
    }

    @Transactional(readOnly = true)
    public Page<User> findAllPaged(Integer page, Integer size) {return repository.findByPermission(1, PageRequest.of(page, size));}

    private boolean checkIfUsernameExists(String username) {return repository.existsByUsername(username);}
}