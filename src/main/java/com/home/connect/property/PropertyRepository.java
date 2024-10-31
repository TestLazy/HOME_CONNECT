package com.home.connect.property;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Integer> {
    Optional<Property> findByDescription(String description);

    Page<Property> findAllByOrderByValueAsc(Pageable pageable);

    Page<Property> findAllByValue(BigDecimal value, Pageable pageable);
}