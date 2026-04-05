package com.elearning.VideoExternalService.Exceptions;

import video.api.client.api.ApiException;

public class ApiVideoNotFoundException extends ApiVideoException {
    public ApiVideoNotFoundException(String message) {
        super(message);
    }

    public ApiVideoNotFoundException(ApiException exception){
        super(exception);
    }
}
