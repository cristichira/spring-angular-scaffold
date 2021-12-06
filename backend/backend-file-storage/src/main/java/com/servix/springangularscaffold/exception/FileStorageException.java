package com.servix.springangularscaffold.exception;

import org.springframework.http.HttpStatus;

public class FileStorageException extends AbstractRuntimeException {

    public FileStorageException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public FileStorageException(String message, Throwable e) {
        super(message, e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
