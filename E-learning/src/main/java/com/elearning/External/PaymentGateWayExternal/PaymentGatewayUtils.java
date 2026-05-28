package com.elearning.External.PaymentGateWayExternal;

import java.util.function.Supplier;

public interface PaymentGatewayUtils {

    public <T> T rateLimitRetry(Supplier<T> action);
    void rateLimitRetry(Runnable runnable);
}
