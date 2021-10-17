package com.servix.springangularscaffold.dto;

import com.servix.springangularscaffold.exception.AbstractRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;

import java.time.LocalDate;

public class ExceptionResponseDto {
    private String timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public ExceptionResponseDto() {
    }

    public ExceptionResponseDto(String timestamp, Integer status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public static ResponseEntity<ExceptionResponseDto> buildResponseEntity(Exception e, String path) {
        HttpStatus httpStatus;
        String message;
        if (AbstractRuntimeException.class.isAssignableFrom(e.getClass())) {
            final AbstractRuntimeException runtimeException = (AbstractRuntimeException) e;
            httpStatus = runtimeException.getStatus();
            message = runtimeException.getMessage();
        } else if (e instanceof AccessDeniedException) {
            message = e.getMessage();
            httpStatus = HttpStatus.UNAUTHORIZED;
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            message = httpStatus.getReasonPhrase();
            e.printStackTrace();
        }

        return ResponseEntity.status(httpStatus)
                .body(new ExceptionResponseDto(
                        LocalDate.now().toString(),
                        httpStatus.value(),
                        httpStatus.getReasonPhrase(),
                        message,
                        path)
                );
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
