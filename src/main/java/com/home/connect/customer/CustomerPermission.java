package com.home.connect.customer;

import com.home.connect.system.exceptions.InvalidPermissionException;

import java.util.Objects;

public enum CustomerPermission {
    ROLE_USER(1),
    ROLE_ADMIN(2);

    private final Integer code;

    CustomerPermission(Integer code) {this.code = code;}

    public static CustomerPermission valueOf(Integer response) {
        for (CustomerPermission permission : CustomerPermission.values())
            if (Objects.equals(permission.getCode(), response))
                return permission;

        throw new InvalidPermissionException();
    }

    public Integer getCode() {
        return code;
    }
}