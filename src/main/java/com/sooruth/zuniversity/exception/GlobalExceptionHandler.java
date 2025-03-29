package com.sooruth.zuniversity.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.UUID;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final String GENERIC_MESSAGE =
            "An unexpected error has occurred and logged.  Please contact support with error code: %s";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        String errorCode = UUID.randomUUID().toString();
        LOG.error(String.format("Error code %s for exception: %s", errorCode, ex.toString()));
        return new ResponseEntity<>(String.format(GENERIC_MESSAGE, errorCode), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}