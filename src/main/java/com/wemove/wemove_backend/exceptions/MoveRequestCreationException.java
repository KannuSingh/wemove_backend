package com.wemove.wemove_backend.exceptions;

public class MoveRequestCreationException extends RuntimeException {
    public MoveRequestCreationException(String message) {
        super(message);
    }
}
