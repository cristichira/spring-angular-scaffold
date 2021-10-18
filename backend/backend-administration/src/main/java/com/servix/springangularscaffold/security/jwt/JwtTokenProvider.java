package com.servix.springangularscaffold.security.jwt;

import com.servix.springangularscaffold.config.YamlConfigurationProperties;
import com.servix.springangularscaffold.security.dto.CurrentUser;
import com.servix.springangularscaffold.security.dto.JwtTokens;
import com.servix.springangularscaffold.security.exception.AuthenticationException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    private final YamlConfigurationProperties yamlConfigurationProperties;
    private String base64EncodedSecretKey;

    public static final String ROLE_REFRESH_TOKEN = "ROLE_REFRESH_TOKEN";
    public static final String COOKIE_ACCESS_TOKEN = "accessToken";
    public static final String COOKIE_REFRESH_TOKEN = "refreshToken";

    @Autowired
    public JwtTokenProvider(YamlConfigurationProperties yamlConfigurationProperties) {
        this.yamlConfigurationProperties = yamlConfigurationProperties;
    }

    @PostConstruct
    protected void init() {
        final String secretKey = yamlConfigurationProperties.getSecurity().getJwt().getToken().getSecretKey();
        base64EncodedSecretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createAccessToken(CurrentUser currentUser) {

        final Claims claims = Jwts.claims();
        claims.put("id", currentUser.getId());
        claims.put("username", currentUser.getUsername());
        claims.put("authorities", currentUser.getAuthorities().toArray());

        final Date now = new Date();
        final Date validity = new Date(now.getTime() + yamlConfigurationProperties.getSecurity().getJwt().getToken().getAccessTokenExpireLength());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, base64EncodedSecretKey)
                .compact();
    }

    public String createRefreshToken(CurrentUser currentUser) {

        Claims claims = Jwts.claims().setSubject(currentUser.getUsername());
        claims.put("scopes", Collections.singletonList(ROLE_REFRESH_TOKEN));

        Date now = new Date();
        Date validity = new Date(now.getTime() + yamlConfigurationProperties.getSecurity().getJwt().getToken().getRefreshTokenExpireLength());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, base64EncodedSecretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        final Claims body = Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(token).getBody();
        final CurrentUser currentUser = claimsToCurrentUser(body);
        return new UsernamePasswordAuthenticationToken(currentUser, "", currentUser.getAuthorities());
    }

    public boolean validateToken(String token) throws JwtException {
        try {
            Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new AuthenticationException("EXPIRED_TOKEN", e);
        } catch (SignatureException e) {
            throw new AuthenticationException("JWT_SIGNATURE_EXCEPTION", e);
        } catch (JwtException e) {
            throw new AuthenticationException("JWT_EXCEPTION", e);
        }
    }

    public Jws<Claims> parseJwsClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw new AuthenticationException("EXPIRED_TOKEN", e);
        } catch (SignatureException e) {
            throw new AuthenticationException("JWT_SIGNATURE_EXCEPTION", e);
        } catch (JwtException e) {
            throw new AuthenticationException("JWT_EXCEPTION", e);
        }
    }

    private CurrentUser claimsToCurrentUser(Claims body) {
        final Long id = body.get("id", Long.class);
        final String username = body.get("username", String.class);
        final ArrayList<LinkedHashMap<String, String>> authorityMapList = (ArrayList<LinkedHashMap<String, String>>) body.get("authorities");
        final Set<SimpleGrantedAuthority> authorities = authorityMapList.stream().map(map -> new SimpleGrantedAuthority((String) ((LinkedHashMap) map).get("authority"))).collect(Collectors.toSet());

        return new CurrentUser(id, username, "-", authorities);
    }

    public JwtTokens createJwtTokens(CurrentUser currentUser) {
        final String accessToken = createAccessToken(currentUser);
        final String refreshToken = createRefreshToken(currentUser);

        return JwtTokens.loginResponseBuilder()
                .withAccessToken(accessToken)
                .withRefreshToken(refreshToken)
                .build();
    }
}
