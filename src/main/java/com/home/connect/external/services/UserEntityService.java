package com.home.connect.external.services;

import com.home.connect.core.entities.UserEntity;
import com.home.connect.core.notifications.UserEntityNotification;
import com.home.connect.core.repositories.UserEntityRepository;
import com.home.connect.external.dtos.UpdateDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserEntityService {
    private final UserEntityRepository repository;

    public UserEntity findById(Integer id) {
        return repository
                .findById(id)
                .orElseThrow(() ->
                        new UserEntityNotification("Usuário não encontrado!"));
    }

    public void updateById(Integer id, UpdateDTO newEntity) {
        var entityExisting = findById(id);

        repository.save(new UserEntity(oldEntity -> {
            oldEntity.setId(entityExisting.getId());
            oldEntity.setEmail(newEntity.getEmail());
            oldEntity.setPassword(newEntity.getPassword());
            oldEntity.setFullName(newEntity.getFullName());
            oldEntity.setPersonalNumber(newEntity.getPersonalNumber());
        }));
    }

    public void deleteById(Integer id) {repository.deleteById(findById(id).getId());}

    public Page<UserEntity> findAllPaginated(Integer page, Integer size) {return repository.findAll(PageRequest.of(page, size));}
}