package com.servix.springangularscaffold.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public abstract class AbstractRuntimeException extends RuntimeException {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRuntimeException.class);
    private final HttpStatus status;

    public AbstractRuntimeException(String message, boolean withLogger) {
        super(message);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (withLogger) {
            LOGGER.error(message);
        }
    }

    public AbstractRuntimeException(String message, HttpStatus status, boolean withLogger) {
        super(message);
        this.status = status;
        if (withLogger) {
            LOGGER.error(message);
        }
    }

    public AbstractRuntimeException(String message, Throwable cause, boolean withLogger) {
        super(message, cause);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (withLogger) {
            LOGGER.error(message, cause);
        }
    }

    public AbstractRuntimeException(String message, Throwable cause, HttpStatus status, boolean withLogger) {
        super(message, cause);
        this.status = status;
        if (withLogger) {
            LOGGER.error(message, cause);
        }
    }

    public AbstractRuntimeException(String message) {
        this(message, true);
    }

    public AbstractRuntimeException(String message, HttpStatus status) {
        this(message, status, true);
    }

    public AbstractRuntimeException(String message, Throwable cause) {
        this(message, cause, true);
    }

    public AbstractRuntimeException(String message, Throwable cause, HttpStatus status) {
        this(message, cause, status, true);
    }

    public HttpStatus getStatus() {
        return status;
    }
}
