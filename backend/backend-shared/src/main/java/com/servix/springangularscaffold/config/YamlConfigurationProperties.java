package com.servix.springangularscaffold.config;

import java.util.List;

public interface YamlConfigurationProperties {
    Security getSecurity();

    Cors getCors();

    Cookies getCookies();

    interface Cookies {
        boolean isSecure();
    }

    interface Cors {
        List<String> getAllowedOrigins();
    }

    interface Security {
        Jwt getJwt();

        interface Jwt {
            Token getToken();

            interface Token {
                String getSecretKey();

                long getAccessTokenExpireLength();

                long getRefreshTokenExpireLength();
            }
        }
    }
}
