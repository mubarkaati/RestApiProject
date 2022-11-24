package com.usman.loginPage.exception;

public class InvalidCredential extends RuntimeException {

    public InvalidCredential() {
        super("Invalid UserId or Password");
    }
}