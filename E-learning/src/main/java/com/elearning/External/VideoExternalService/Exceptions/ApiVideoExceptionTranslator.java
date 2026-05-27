package com.elearning.External.VideoExternalService.Exceptions;

import org.springframework.stereotype.Component;
import video.api.client.api.ApiException;

@Component
public final class ApiVideoExceptionTranslator {


    public ApiVideoException translate(ApiException e)
    {
        return switch (e.getCode()) {
            case 400 -> new ApiVideoInvalidParametersException(e);
            case 401 -> new ApiVideoAuthenticationException(e);
            case 404 -> new ApiVideoNotFoundException(e);
            case 429 -> new ApiVideoRateLimiterException(e);
            case 500, 501, 503 -> new ApiVideoUnavailableException(e);
            default ->
                    throw new IllegalArgumentException("Api video exception translator can't translate exception: " + e);
        };
    }
}
