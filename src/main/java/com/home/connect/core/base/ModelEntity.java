package com.home.connect.core.base;

import com.home.connect.core.security.Permission;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.Consumer;

@MappedSuperclass
public abstract class ModelEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, length = 25, nullable = false)
    private String username;

    @Column(length = 60, nullable = false)
    private String password;

    @Column(length = 35, nullable = false)
    private String fullName;

    @Column(length = 1, nullable = false)
    private Integer permission;

    @Column(unique = true, length = 14, nullable = false)
    private String personalNumber;

    protected ModelEntity() {}

    protected ModelEntity(Consumer<ModelEntity> entity) {entity.accept(this);}

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getFullName() {return fullName;}

    public void setFullName(String fullName) {this.fullName = fullName;}

    public Permission getPermission() {return Permission.valueOf(permission);}

    public void setPermission(Permission permission) {this.permission = permission.getCode();}

    public String getPersonalNumber() {return personalNumber;}

    public void setPersonalNumber(String personalNumber) {this.personalNumber = personalNumber;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelEntity that = (ModelEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {return Objects.hashCode(id);}
}