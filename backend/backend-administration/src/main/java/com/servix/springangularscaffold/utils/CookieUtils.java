package com.servix.springangularscaffold.utils;

import com.servix.springangularscaffold.config.YamlConfigurationProperties;
import com.servix.springangularscaffold.enumeration.CookieKeys;
import com.servix.springangularscaffold.security.dto.JwtTokens;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Component
public class CookieUtils {
    private final YamlConfigurationProperties yamlConfigurationProperties;

    public CookieUtils(YamlConfigurationProperties yamlConfigurationProperties) {
        this.yamlConfigurationProperties = yamlConfigurationProperties;
    }

    public static Optional<String> resolveCookie(HttpServletRequest request, String cookieName) {
        return Objects.isNull(request.getCookies()) ?
                Optional.empty() :
                Arrays.stream(request.getCookies())
                        .filter(cookie -> StringUtils.equals(cookieName, cookie.getName()))
                        .map(Cookie::getValue)
                        .findAny();
    }

    public void addJwtTokens(JwtTokens jwtTokens, HttpServletResponse response) {
        Stream.of(createJwtTokenCookies(jwtTokens)).forEach(response::addCookie);
    }

    public void clearJwtTokens(HttpServletResponse response) {
        final Cookie accessTokenCookie = new Cookie(CookieKeys.ACCESS_TOKEN_KEY, null);
        final Cookie refreshTokenCookie = new Cookie(CookieKeys.REFRESH_TOKEN_KEY, null);

        accessTokenCookie.setSecure(yamlConfigurationProperties.getCookies().isSecure());
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setPath("/");
        accessTokenCookie.setMaxAge(0);

        refreshTokenCookie.setSecure(yamlConfigurationProperties.getCookies().isSecure());
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(0);

        response.addCookie(accessTokenCookie);
        response.addCookie(refreshTokenCookie);
    }

    public Cookie[] createJwtTokenCookies(JwtTokens jwtTokens) {
        final Cookie accessTokenCookie = new Cookie(CookieKeys.ACCESS_TOKEN_KEY, jwtTokens.getAccessToken());
        final Cookie refreshTokenCookie = new Cookie(CookieKeys.REFRESH_TOKEN_KEY, jwtTokens.getRefreshToken());

        accessTokenCookie.setMaxAge((int) yamlConfigurationProperties.getSecurity().getJwt().getToken().getAccessTokenExpireLength() / 1000);
        accessTokenCookie.setSecure(yamlConfigurationProperties.getCookies().isSecure());
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setPath("/");

        refreshTokenCookie.setMaxAge((int) yamlConfigurationProperties.getSecurity().getJwt().getToken().getRefreshTokenExpireLength() / 1000);
        refreshTokenCookie.setSecure(yamlConfigurationProperties.getCookies().isSecure());
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setPath("/");

        return new Cookie[]{accessTokenCookie, refreshTokenCookie};
    }
}
