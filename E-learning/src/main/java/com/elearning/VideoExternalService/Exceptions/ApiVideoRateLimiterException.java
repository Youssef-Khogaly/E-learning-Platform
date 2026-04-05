package com.elearning.VideoExternalService.Exceptions;

import lombok.Getter;
import video.api.client.api.ApiException;

@Getter
public class ApiVideoRateLimiterException extends ApiVideoException {
    private long retryAfterInSeconds;
    public ApiVideoRateLimiterException(String message) {
        super(message);
    }
    public ApiVideoRateLimiterException(String message ,ApiException apiException){
        super(message);
        retryAfterInSeconds = Long.parseLong(apiException.getResponseHeaders().get("X-RateLimit-Retry-After").get(0));

    }
    public ApiVideoRateLimiterException(ApiException apiException){
        super(apiException);
        retryAfterInSeconds = Long.parseLong(apiException.getResponseHeaders().get("X-RateLimit-Retry-After").get(0));

    }
    public ApiVideoRateLimiterException(Throwable cause) {
        super(cause);
    }
}
