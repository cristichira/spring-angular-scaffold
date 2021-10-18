package com.servix.springangularscaffold.service;


import com.servix.springangularscaffold.security.dto.JwtTokens;
import com.servix.springangularscaffold.security.dto.LoginRequest;

public interface AuthenticationService {

    JwtTokens login(LoginRequest loginRequest);

    JwtTokens refreshToken(String refreshToken);
}
