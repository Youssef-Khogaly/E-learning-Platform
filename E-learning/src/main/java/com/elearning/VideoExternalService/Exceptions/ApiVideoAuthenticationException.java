package com.elearning.VideoExternalService.Exceptions;

import video.api.client.api.ApiException;

public class ApiVideoAuthenticationException extends ApiVideoException {
    public ApiVideoAuthenticationException(String message) {
        super(message);
    }
    public ApiVideoAuthenticationException(ApiException apiException){
        super(apiException);
    }
}
