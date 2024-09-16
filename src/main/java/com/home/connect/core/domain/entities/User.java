package com.home.connect.core.domain.entities;

import com.home.connect.core.base.ModelEntity;
import jakarta.persistence.Entity;

import java.util.function.Consumer;

@Entity
public class User extends ModelEntity {
    public User() {}
    
    public User(Consumer<ModelEntity> entity) {super(entity);}
}