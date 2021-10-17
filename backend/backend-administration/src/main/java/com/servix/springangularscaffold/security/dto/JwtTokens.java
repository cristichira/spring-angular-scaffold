package com.servix.springangularscaffold.security.dto;

public class JwtTokens {
    private String accessToken;
    private String refreshToken;

    public static JwtTokensBuilder loginResponseBuilder() {
        return new JwtTokensBuilder();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public static class JwtTokensBuilder {
        private String accessToken;
        private String refreshToken;

        public JwtTokensBuilder withAccessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public JwtTokensBuilder withRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public JwtTokens build() {
            final JwtTokens jwtTokens = new JwtTokens();
            jwtTokens.accessToken = this.accessToken;
            jwtTokens.refreshToken = this.refreshToken;

            return jwtTokens;
        }
    }
}
