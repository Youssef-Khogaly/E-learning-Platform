package com.elearning.External.VideoExternalService;

import java.util.function.*;

public interface ApiVideoUtils {
    /*
        if api.video throw rate limit exception
        will retry after x second depend on api.video exception
     */
    public <T> T rateLimitRetry(Supplier<T> action);
    void rateLimitRetry(Runnable runnable);
}
