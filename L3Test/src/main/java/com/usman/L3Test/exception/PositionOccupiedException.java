package com.usman.L3Test.exception;

public class PositionOccupiedException extends RuntimeException {
    public PositionOccupiedException() {
        super("given position is occupied by opposite player");
    }
}