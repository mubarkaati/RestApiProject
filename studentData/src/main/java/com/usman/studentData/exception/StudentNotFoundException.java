package com.usman.studentData.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException() {
        super("student Not Found with the given Id");
    }
}