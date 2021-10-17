package com.servix.springangularscaffold.security;

import com.servix.springangularscaffold.security.dto.CurrentUser;
import com.servix.springangularscaffold.service.SecurityService;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Override
    public CurrentUser getCurrentUser() {
        final SecurityContext context = SecurityContextHolder.getContext();

        if (Objects.nonNull(context) && Objects.nonNull(context.getAuthentication())) {
            final Object principal = context.getAuthentication().getPrincipal();
            if (principal instanceof CurrentUser) {
                return (CurrentUser) principal;
            }
        }

        return null;
    }
}
