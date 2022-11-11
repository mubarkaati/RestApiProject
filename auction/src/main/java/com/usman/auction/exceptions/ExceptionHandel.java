package com.usman.auction.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ExceptionHandel {
    public static String message;
    public static HttpStatus httpStatus;
}