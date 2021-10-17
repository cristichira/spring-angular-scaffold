package com.servix.springangularscaffold.security;

import com.servix.springangularscaffold.security.dto.CurrentUser;
import com.servix.springangularscaffold.security.dto.JwtTokens;
import com.servix.springangularscaffold.security.dto.LoginRequest;
import com.servix.springangularscaffold.security.exception.AuthenticationException;
import com.servix.springangularscaffold.security.jwt.JwtTokenProvider;
import com.servix.springangularscaffold.service.AuthenticationService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Autowired
    public AuthenticationServiceImpl(JwtTokenProvider jwtTokenProvider,
                                     AuthenticationManager authenticationManager,
                                     UserDetailsService userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @Override
    @Transactional
    public JwtTokens login(LoginRequest loginRequest) {
        final String username = loginRequest.getUsername();
        final String password = loginRequest.getPassword();

        try {
            final Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(auth);

            final CurrentUser currentUser = (CurrentUser) auth.getPrincipal();
            return jwtTokenProvider.createJwtTokens(currentUser);
        } catch (DisabledException e) {
            throw new AuthenticationException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("INVALID_CREDENTIALS", e);
        } catch (org.springframework.security.core.AuthenticationException e) {
            throw new AuthenticationException("INVALID_USERNAME_OR_PASSWORD", e);
        }
    }

    @Override
    public JwtTokens refreshToken(String refreshToken) {
        final Jws<Claims> claimsJws = jwtTokenProvider.parseJwsClaims(refreshToken);

        final List<String> scopes = claimsJws.getBody().get("scopes", List.class);
        if (scopes == null || scopes.isEmpty()
                || scopes.stream().noneMatch(JwtTokenProvider.ROLE_REFRESH_TOKEN::equals)) {
            throw new AuthenticationException("INVALID_REFRESH_TOKEN");
        }

        final String username = claimsJws.getBody().getSubject();
        final CurrentUser currentUser = (CurrentUser) userDetailsService.loadUserByUsername(username);

        final JwtTokens jwtTokens = jwtTokenProvider.createJwtTokens(currentUser);
        final Authentication auth = jwtTokenProvider.getAuthentication(jwtTokens.getAccessToken());
        SecurityContextHolder.getContext().setAuthentication(auth);

        return jwtTokens;
    }
}
