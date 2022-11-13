package com.usman.auction.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NumberExceptionController {
    @ExceptionHandler(value = NumberNotAvailableException.class)
    public ResponseEntity<Object> exception(NumberNotAvailableException numberNotAvailableException) {
        return new ResponseEntity<>("number is not available", HttpStatus.BAD_REQUEST);
    }
}