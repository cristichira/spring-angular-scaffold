package com.servix.springangularscaffold.permission;

import com.servix.springangularscaffold.security.dto.CurrentUser;
import com.servix.springangularscaffold.security.permission.PermissionChecker;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;

import static com.servix.springangularscaffold.security.permission.PerformedOperation.SINGLE_ENTITY;

@Component(value = "UserPermissionChecker")
public class UserPermissionChecker implements PermissionChecker {
    public static final String USER_PERMISSION_CHECKER = "UserPermissionChecker";

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String operation) {
        if (Objects.isNull(targetId)) {
            return true;
        }

        if (Objects.isNull(authentication)) {
            return false;
        }

        switch (operation) {
            case SINGLE_ENTITY:
                return userHasAccessToUser(authentication, targetId);
            default:
                return false;
        }
    }

    private boolean userHasAccessToUser(Authentication authentication, Serializable targetId) {
        final Long currentUserId = ((CurrentUser) authentication.getPrincipal()).getId();
        final Long userId = (Long) targetId;

        return Objects.equals(currentUserId, userId);
    }
}
