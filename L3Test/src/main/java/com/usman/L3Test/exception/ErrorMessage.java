package com.usman.L3Test.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorMessage {
    public static String message;
    public static HttpStatus httpStatus;
}