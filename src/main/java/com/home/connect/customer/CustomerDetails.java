package com.home.connect.customer;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomerDetails implements UserDetails {
    private final Customer entity;

    public CustomerDetails(Customer entity) {
        this.entity = entity;
    }

    @Override
    public String getUsername() {
        return entity.getUsername();
    }

    @Override
    public String getPassword() {
        return entity.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(String.valueOf(entity.getPermission())));
    }
}