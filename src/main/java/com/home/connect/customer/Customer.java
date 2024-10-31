package com.home.connect.customer;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.home.connect.property.Property;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

@Entity
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 25, unique = true)
    private String username;

    @Column(length = 60, nullable = false)
    private String password;

    @Column(length = 25, nullable = false)
    private String fullName;

    @Column(length = 1, nullable = false)
    private Integer permission;

    @Column(length = 11, unique = true)
    private String personalNumber;

    @JsonManagedReference
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Property> properties = new HashSet<>();

    public Customer() {}

    public Customer(Consumer<Customer> entity) {
        entity.accept(this);
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public CustomerPermission getPermission() {
        return CustomerPermission.valueOf(permission);
    }

    public void setPermission(CustomerPermission permission) {
        this.permission = permission.getCode();
    }

    public Set<Property> getProperties() {
        return properties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}