package com.servix.springangularscaffold.controller;

import com.servix.springangularscaffold.dto.ExceptionResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class SharedExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDto> processUnauthorizedException(HttpServletRequest request, Exception e) {
        return ExceptionResponseDto.buildResponseEntity(e, request.getServletPath());
    }
}
