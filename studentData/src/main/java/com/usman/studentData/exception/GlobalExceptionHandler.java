package com.usman.studentData.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<Object> studentNotFoundResponse(Exception exception, WebRequest webRequest) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), exception.getMessage());
        return handleExceptionInternal(exception, errorResponse, null, HttpStatus.NOT_FOUND, webRequest);
    }

    @ExceptionHandler(NoStudentExistException.class)
    public ResponseEntity<Object> noStudentExistResponse(Exception exception, WebRequest webRequest) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), exception.getMessage());
        return handleExceptionInternal(exception, errorResponse, null, HttpStatus.NOT_FOUND, webRequest);
    }

    @ExceptionHandler(NoSubjectExistException.class)
    public ResponseEntity<Object> noSubjectExistResponse(Exception exception, WebRequest webRequest) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), exception.getMessage());
        return handleExceptionInternal(exception, errorResponse, null, HttpStatus.NOT_FOUND, webRequest);
    }

    public ResponseEntity<Object> subjectNotFoundResponse(Exception exception, WebRequest webRequest) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), exception.getMessage());
        return handleExceptionInternal(exception, errorResponse, null, HttpStatus.NOT_FOUND, webRequest);
    }
}