package com.servix.springangularscaffold.facade;

import com.servix.springangularscaffold.dto.auth.LoginResponse;
import com.servix.springangularscaffold.dto.user.RegisterDto;
import com.servix.springangularscaffold.security.dto.LoginRequest;

public interface AuthenticationFacade {

    LoginResponse login(LoginRequest loginRequest);

    LoginResponse register(RegisterDto registerDto);

    LoginResponse refreshToken(String refreshToken);
}
