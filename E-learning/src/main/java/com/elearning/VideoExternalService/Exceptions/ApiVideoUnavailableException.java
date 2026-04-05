package com.elearning.VideoExternalService.Exceptions;

import video.api.client.api.ApiException;

public class ApiVideoUnavailableException extends ApiVideoException {
    public ApiVideoUnavailableException(String message) {
        super(message);
    }
    public ApiVideoUnavailableException(ApiException apiException){
        super(apiException);
    }
}
