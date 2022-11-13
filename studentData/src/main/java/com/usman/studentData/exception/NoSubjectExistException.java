package com.usman.studentData.exception;

public class NoSubjectExistException extends RuntimeException {
    public NoSubjectExistException() {
        super("no subject exist in Database");
    }
}