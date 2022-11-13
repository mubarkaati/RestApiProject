package com.usman.studentData.exception;

public class NoStudentExistException extends RuntimeException {
    public NoStudentExistException() {
        super("no student exist in Database");
    }
}