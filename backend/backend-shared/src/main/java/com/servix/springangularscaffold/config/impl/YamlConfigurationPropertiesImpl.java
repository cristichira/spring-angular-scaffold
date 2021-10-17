package com.servix.springangularscaffold.config.impl;

import com.servix.springangularscaffold.config.YamlConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties("custom-app-config")
public class YamlConfigurationPropertiesImpl implements YamlConfigurationProperties {
    private CookiesImpl cookies;
    private CorsImpl cors;
    private SecurityImpl security;

    @Override
    public CorsImpl getCors() {
        return cors;
    }

    public void setCors(CorsImpl cors) {
        this.cors = cors;
    }

    @Override
    public SecurityImpl getSecurity() {
        return security;
    }

    public void setSecurity(SecurityImpl security) {
        this.security = security;
    }

    @Override
    public CookiesImpl getCookies() {
        return cookies;
    }

    public void setCookies(CookiesImpl cookies) {
        this.cookies = cookies;
    }

    public static class CookiesImpl implements Cookies {
        private boolean secure;

        public boolean isSecure() {
            return secure;
        }

        public void setSecure(boolean secure) {
            this.secure = secure;
        }
    }

    public static class CorsImpl implements Cors {
        private List<String> allowedOrigins = new ArrayList<>();

        @Override
        public List<String> getAllowedOrigins() {
            return allowedOrigins;
        }

        public void setAllowedOrigins(List<String> allowedOrigins) {
            this.allowedOrigins = allowedOrigins;
        }
    }

    public static class SecurityImpl implements Security {
        private JwtImpl jwt;

        @Override
        public JwtImpl getJwt() {
            return jwt;
        }

        public void setJwt(JwtImpl jwt) {
            this.jwt = jwt;
        }

        public static class JwtImpl implements Jwt {
            private TokenImpl token;

            @Override
            public TokenImpl getToken() {
                return token;
            }

            public void setToken(TokenImpl token) {
                this.token = token;
            }

            public static class TokenImpl implements Token {
                private String secretKey;
                private long accessTokenExpireLength;
                private long refreshTokenExpireLength;

                @Override
                public String getSecretKey() {
                    return secretKey;
                }

                public void setSecretKey(String secretKey) {
                    this.secretKey = secretKey;
                }

                @Override
                public long getAccessTokenExpireLength() {
                    return accessTokenExpireLength;
                }

                public void setAccessTokenExpireLength(long accessTokenExpireLength) {
                    this.accessTokenExpireLength = accessTokenExpireLength;
                }

                @Override
                public long getRefreshTokenExpireLength() {
                    return refreshTokenExpireLength;
                }

                public void setRefreshTokenExpireLength(long refreshTokenExpireLength) {
                    this.refreshTokenExpireLength = refreshTokenExpireLength;
                }
            }
        }
    }
}
