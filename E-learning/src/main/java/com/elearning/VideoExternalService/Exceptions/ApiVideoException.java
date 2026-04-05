package com.elearning.VideoExternalService.Exceptions;

public class ApiVideoException extends RuntimeException {
    public ApiVideoException(String message) {
        super(message);
    }

    public ApiVideoException(Throwable cause) {
        super(cause);
    }

    public ApiVideoException(String message, Throwable cause) {
        super(message, cause);
    }
}
