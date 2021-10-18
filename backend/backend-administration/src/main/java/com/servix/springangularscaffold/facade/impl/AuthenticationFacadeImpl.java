package com.servix.springangularscaffold.facade.impl;

import com.servix.springangularscaffold.dto.auth.LoginResponse;
import com.servix.springangularscaffold.dto.user.RegisterDto;
import com.servix.springangularscaffold.dto.user.UserDto;
import com.servix.springangularscaffold.facade.AuthenticationFacade;
import com.servix.springangularscaffold.mapper.UserMapper;
import com.servix.springangularscaffold.security.dto.JwtTokens;
import com.servix.springangularscaffold.security.dto.LoginRequest;
import com.servix.springangularscaffold.service.AuthenticationService;
import com.servix.springangularscaffold.service.SecurityService;
import com.servix.springangularscaffold.service.UserService;
import com.servix.springangularscaffold.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    private final AuthenticationService authenticationService;
    private final SecurityService securityService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final CookieUtils cookieUtils;

    @Autowired
    public AuthenticationFacadeImpl(AuthenticationService authenticationService,
                                    SecurityService securityService,
                                    UserService userService,
                                    UserMapper userMapper,
                                    CookieUtils cookieUtils) {
        this.authenticationService = authenticationService;
        this.securityService = securityService;
        this.userService = userService;
        this.userMapper = userMapper;
        this.cookieUtils = cookieUtils;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        final JwtTokens jwtTokens = authenticationService.login(loginRequest);
        final UserDto currentUser = userMapper.toDto(userService.findById(securityService.getCurrentUser().getId()));

        return LoginResponse.loginResponseBuilder()
                .withCurrentUser(currentUser)
                .withJwtTokens(jwtTokens)
                .build();
    }

    @Override
    public LoginResponse register(RegisterDto registerDto) {
        userService.register(registerDto);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(registerDto.getEmail());
        loginRequest.setPassword(registerDto.getPassword());

        return login(loginRequest);
    }

    @Override
    public LoginResponse refreshToken(String refreshToken) {
        final JwtTokens jwtTokens = authenticationService.refreshToken(refreshToken);
        final UserDto currentUser = userMapper.toDto(userService.findById(securityService.getCurrentUser().getId()));

        return LoginResponse.loginResponseBuilder()
                .withCurrentUser(currentUser)
                .withJwtTokens(jwtTokens)
                .build();
    }
}
