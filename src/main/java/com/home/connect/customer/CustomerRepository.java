package com.home.connect.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByUsername(String username);

    Page<Customer> findAllByOrderByUsernameAsc(Pageable pageable);

    Boolean existsByUsername(String username);

    Boolean existsByPersonalNumber(String personalNumber);
}