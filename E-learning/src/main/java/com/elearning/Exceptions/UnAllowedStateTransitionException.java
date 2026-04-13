package com.elearning.Exceptions;

public class UnAllowedStateTransitionException extends RuntimeException {
    public UnAllowedStateTransitionException(String message) {
        super(message);
    }
}
