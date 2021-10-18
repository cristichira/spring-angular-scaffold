package com.servix.springangularscaffold.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends AbstractRuntimeException {

    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
