package com.servix.springangularscaffold.dto.auth;

import com.servix.springangularscaffold.dto.user.UserDto;
import com.servix.springangularscaffold.security.dto.JwtTokens;

public class LoginResponse {
    private JwtTokens jwtTokens;
    private UserDto currentUser;

    public static LoginResponseBuilder loginResponseBuilder() {
        return new LoginResponseBuilder();
    }

    public JwtTokens getJwtTokens() {
        return jwtTokens;
    }

    public UserDto getCurrentUser() {
        return currentUser;
    }

    public static class LoginResponseBuilder {
        private JwtTokens jwtTokens;
        private UserDto currentUser;

        public LoginResponseBuilder withJwtTokens(JwtTokens jwtTokens) {
            this.jwtTokens = jwtTokens;
            return this;
        }

        public LoginResponseBuilder withCurrentUser(UserDto currentUser) {
            this.currentUser = currentUser;
            return this;
        }

        public LoginResponse build() {
            final LoginResponse loginResponse = new LoginResponse();
            loginResponse.jwtTokens = this.jwtTokens;
            loginResponse.currentUser = this.currentUser;

            return loginResponse;
        }
    }
}
