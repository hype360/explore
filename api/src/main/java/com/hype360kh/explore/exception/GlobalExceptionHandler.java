package com.hype360kh.explore.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    protected org.springframework.http.ResponseEntity<Object> handleMismatchedInputException(
            ResourceNotFoundException e, HttpStatusCode status) {
        log.warn(e.getMessage());
        return new org.springframework.http.ResponseEntity<>(e.getMessage(), status);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ResourceBadRequestException.class)
    protected org.springframework.http.ResponseEntity<Object> handleMismatchedInputException(
            ResourceBadRequestException e, HttpStatusCode status) {
        log.warn(e.getMessage());
        return new org.springframework.http.ResponseEntity<>(e.getMessage(), status);
    }
}
