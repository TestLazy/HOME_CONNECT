package com.home.connect.core.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.function.Consumer;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends BaseEntity {
    @Column(unique = true, length = 15)
    private String email;

    @Column(length = 60)
    private String password;

    @Column(length = 60)
    private String fullName;

    @Column(length = 1)
    private Integer permission;

    @Column(unique = true, length = 15)
    private String personalNumber;

    public UserEntity(Consumer<UserEntity> entity) {entity.accept(this);}

    public Permission getPermission() {return Permission.valueOf(permission);}

    public void setPermission(Permission permission) {this.permission = permission.getCode();}
}