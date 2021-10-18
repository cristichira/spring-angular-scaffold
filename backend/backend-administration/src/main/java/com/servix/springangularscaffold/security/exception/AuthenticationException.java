package com.servix.springangularscaffold.security.exception;

import com.servix.springangularscaffold.exception.AbstractRuntimeException;
import org.springframework.http.HttpStatus;

public class AuthenticationException extends AbstractRuntimeException {

    public AuthenticationException(String message) {
        super(message, HttpStatus.UNAUTHORIZED, false);
    }

    public AuthenticationException(String message, Exception e) {
        super(message, e, HttpStatus.UNAUTHORIZED, false);
    }
}
