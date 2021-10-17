package com.servix.springangularscaffold.exception;

public class ApplicationRuntimeException extends AbstractRuntimeException {

    public ApplicationRuntimeException(String message) {
        super(message);
    }

    public ApplicationRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
