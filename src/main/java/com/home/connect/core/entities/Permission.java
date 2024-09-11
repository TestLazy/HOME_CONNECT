package com.home.connect.core.entities;

import com.home.connect.core.notifications.PermissionNotification;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Permission {
    ROLE_ADMIN(1),
    ROLE_CLIENT(2);

    private final Integer code;

    public static Permission valueOf(Integer response) {
        for (var role : Permission.values())
            if (Objects.equals(role.getCode(), response))
                return role;

        throw new PermissionNotification("Permissão inválida, apenas entre (1 - 2)!");
    }
}