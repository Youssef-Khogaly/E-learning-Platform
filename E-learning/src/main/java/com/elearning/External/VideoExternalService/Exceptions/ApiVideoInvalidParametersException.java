package com.elearning.External.VideoExternalService.Exceptions;

import video.api.client.api.ApiException;

public class ApiVideoInvalidParametersException extends ApiVideoException {
    public ApiVideoInvalidParametersException(String message) {
        super(message);
    }
    public ApiVideoInvalidParametersException(ApiException exception){
        super(exception);
    }
}
