package com.elearning.Exceptions;

public class NotAllowedOperation extends RuntimeException {
    public NotAllowedOperation(String message) {
        super(message);
    }
}
