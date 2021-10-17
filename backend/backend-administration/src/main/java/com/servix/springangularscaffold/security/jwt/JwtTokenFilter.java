package com.servix.springangularscaffold.security.jwt;

import com.servix.springangularscaffold.enumeration.CookieKeys;
import com.servix.springangularscaffold.security.exception.AuthenticationException;
import com.servix.springangularscaffold.utils.CookieUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final String[] EXCLUDE_URLS = new String[]{"/api/auth/refresh-token"};
    private final JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return Arrays.stream(EXCLUDE_URLS).anyMatch(e -> new AntPathMatcher().match(e, request.getRequestURI()));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws IOException, ServletException {
        final Optional<String> optionalAccessToken = CookieUtils.resolveCookie(httpServletRequest, CookieKeys.ACCESS_TOKEN_KEY);
        try {
            if (optionalAccessToken.isPresent() && jwtTokenProvider.validateToken(optionalAccessToken.get())) {
                final Authentication auth = jwtTokenProvider.getAuthentication(optionalAccessToken.get());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (AuthenticationException e) {
            SecurityContextHolder.clearContext();
            throw e;
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
