package com.servix.springangularscaffold.service;

import com.servix.springangularscaffold.security.dto.CurrentUser;

public interface SecurityService {

    CurrentUser getCurrentUser();
}
