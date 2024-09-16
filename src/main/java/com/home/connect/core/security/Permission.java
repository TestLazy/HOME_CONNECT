package com.home.connect.core.security;

import com.home.connect.core.domain.exceptions.InvalidPermissionException;

import java.util.Objects;

public enum Permission {
    ROLE_USER(1),
    ROLE_ADMIN(2),
    ROLE_SUPPORT(3);

    private final Integer code;

    private Permission(Integer code) {this.code = code;}

    public static Permission valueOf(Integer response) {
        for (var role : Permission.values())
            if (Objects.equals(role.getCode(), response))
                return role;

        throw new InvalidPermissionException();
    }

    public Integer getCode() {return code;}
}