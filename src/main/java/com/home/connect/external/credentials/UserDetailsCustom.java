package com.home.connect.external.credentials;

import com.home.connect.core.domain.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsCustom implements UserDetails {
    private final User entity;

    public UserDetailsCustom(User entity) {this.entity = entity;}

    @Override
    public String getUsername() {return entity.getUsername();}

    @Override
    public String getPassword() {return entity.getPassword();}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {return List.of(new SimpleGrantedAuthority(String.valueOf(entity.getPermission())));}
}