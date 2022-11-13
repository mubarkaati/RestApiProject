package com.usman.studentData.exception;

public class SubjectNotFoundException extends RuntimeException {
    public SubjectNotFoundException() {
        super("subject Not Found with the given Id");
    }
}