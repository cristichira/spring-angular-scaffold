package com.servix.springangularscaffold.security.permission;

import com.servix.springangularscaffold.exception.ApplicationRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class PermissionEvaluatorImpl implements PermissionEvaluator {

    private final ApplicationContext applicationContext;

    @Autowired
    public PermissionEvaluatorImpl(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        throw new ApplicationRuntimeException("Method 'hasPermission' not implemented in 'PermissionEvaluatorImpl'");
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return ((PermissionChecker) applicationContext.getBean(targetType)).hasPermission(authentication, targetId, permission.toString());
    }
}
