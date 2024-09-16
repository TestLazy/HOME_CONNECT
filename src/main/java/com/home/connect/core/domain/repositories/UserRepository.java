package com.home.connect.core.domain.repositories;

import com.home.connect.core.domain.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    Page<User> findByPermission(Integer permission, Pageable pageable);

    Boolean existsByUsername(String username);

    Boolean existsByPersonalNumber(String personalNumber);
}