package com.servix.springangularscaffold.controller;

import com.servix.springangularscaffold.dto.auth.LoginResponse;
import com.servix.springangularscaffold.dto.user.RegisterDto;
import com.servix.springangularscaffold.dto.user.UserDto;
import com.servix.springangularscaffold.enumeration.CookieKeys;
import com.servix.springangularscaffold.facade.AuthenticationFacade;
import com.servix.springangularscaffold.security.dto.LoginRequest;
import com.servix.springangularscaffold.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationFacade authenticationFacade;
    private final CookieUtils cookieUtils;

    @Autowired
    public AuthenticationController(AuthenticationFacade authenticationFacade,
                                    CookieUtils cookieUtils) {
        this.authenticationFacade = authenticationFacade;
        this.cookieUtils = cookieUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        final LoginResponse loginResponse = authenticationFacade.login(loginRequest);
        cookieUtils.addJwtTokens(loginResponse.getJwtTokens(), response);
        return ResponseEntity.ok(loginResponse.getCurrentUser());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        cookieUtils.clearJwtTokens(response);
        SecurityContextHolder.clearContext();
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<UserDto> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        final Optional<String> optionalRefreshToken = CookieUtils.resolveCookie(request, CookieKeys.REFRESH_TOKEN_KEY);
        if (optionalRefreshToken.isPresent()) {
            final LoginResponse loginResponse = authenticationFacade.refreshToken(optionalRefreshToken.get());
            cookieUtils.addJwtTokens(loginResponse.getJwtTokens(), response);
            return ResponseEntity.ok(loginResponse.getCurrentUser());
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody RegisterDto registerDto, HttpServletResponse response) {
        final LoginResponse loginResponse = authenticationFacade.register(registerDto);
        cookieUtils.addJwtTokens(loginResponse.getJwtTokens(), response);
        return ResponseEntity.ok(loginResponse.getCurrentUser());
    }
}
