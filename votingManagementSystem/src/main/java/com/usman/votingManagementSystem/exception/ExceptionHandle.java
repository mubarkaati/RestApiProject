package com.usman.votingManagementSystem.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ExceptionHandle {
    public static String message;
    public static HttpStatus httpStatus;
}