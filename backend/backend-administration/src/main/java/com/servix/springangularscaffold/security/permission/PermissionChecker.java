package com.servix.springangularscaffold.security.permission;

import org.springframework.security.core.Authentication;

import java.io.Serializable;

public interface PermissionChecker {
    boolean hasPermission(Authentication authentication, Serializable targetId, String operation);
}
