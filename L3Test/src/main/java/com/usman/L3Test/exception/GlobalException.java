package com.usman.L3Test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {
    @ExceptionHandler(PositionOccupiedException.class)
    public ResponseEntity<Object> positionOccupiedExceptionResponse(Exception exception) {
        return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
}