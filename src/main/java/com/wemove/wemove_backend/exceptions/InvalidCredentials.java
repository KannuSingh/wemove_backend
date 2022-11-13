package com.wemove.wemove_backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InvalidCredentials  extends RuntimeException {
    public InvalidCredentials(String message) {
        super(message);
    }
}
