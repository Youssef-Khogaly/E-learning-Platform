package com.elearning.External.PaymentGateWayExternal;

import com.elearning.External.PaymentGateWayExternal.Exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Component
@Slf4j
public class StripeUtilsImpl implements PaymentGatewayUtils{
    private static final int MAX_RETRIES = 5;
    @Override
    public <T> T rateLimitRetry(Supplier<T> action) {

        for(int i = 0 ; i < MAX_RETRIES ; ++i)
        {
            try{
                return action.get();
            }
            catch (GateWayRateLimitException e) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500L);
                } catch (InterruptedException ex) {
                    // should never be interrupted
                    // clear flag
                    Thread.currentThread().interrupt();
                    log.error("unexpected interrupt for rate limit retry utils");
                    throw new RuntimeException(e);
                }
            }
        }

        return null;
    }

    @Override
    public void rateLimitRetry(Runnable runnable) {
        for(int i = 0 ; i < MAX_RETRIES ; ++i)
        {
            try{
                runnable.run();
            }
            catch (GateWayRateLimitException e) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500L);
                } catch (InterruptedException ex) {
                    // should never be interrupted
                    // clear flag
                    Thread.currentThread().interrupt();
                    log.error("unexpected interrupt for rate limit retry utils");
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
