package com.elearning.webhook.payment.Stripe;

import com.elearning.webhook.payment.Interfaces.PaymentWebhookHandler;
import com.elearning.webhook.payment.Interfaces.PaymentWebhookHandlerRetries;
import com.elearning.webhook.payment.Interfaces.PaymentWebhookPublisher;
import com.elearning.webhook.payment.PaymentWebhookEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

import java.util.concurrent.*;

@Slf4j
@Service
public class StripeHookPublisher implements PaymentWebhookPublisher,PaymentWebhookHandlerRetries  {

    private final ExecutorService executorService;
    private final PaymentWebhookHandler handler;
    private static final int coreSize = 2;
    private static final int maxPoolSize = 3;
    private static final int keepAliveMilli = 10000;
    private static final int queueSize = 512;
    private static final int MAX_RETRIES = 5;


    public StripeHookPublisher(PaymentWebhookHandler handler) {
        this.handler = handler;
        ThreadFactory threadFactory = (t) ->{
            var thread = new Thread(t);
            thread.setName("stripe webhook worker");
            return thread;
        };
        this.executorService = new ThreadPoolExecutor(coreSize,maxPoolSize,keepAliveMilli
                , TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize) , threadFactory);
    }
    private Runnable getRunnable(PaymentWebhookEvent event)
    {
        return () -> {
                handleWithRetries(() -> handler.handle(event));
        };

    }

    @Override
    public void publish(PaymentWebhookEvent event) {
        var runnable = getRunnable(event);

        executorService.execute(runnable);
    }

    @Override
    public void handleWithRetries(Runnable runnable) {
        Exception lastException = null;
        for(int i = 1 ; i <= MAX_RETRIES ; ++i)
        {
            try{
                runnable.run();
                return;
            }catch (ConcurrencyFailureException e)
            {
                lastException = e;
                try{
                    TimeUnit.MILLISECONDS.sleep(50L * i);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    log.error(ex.getMessage());
                    break;
                }
            }
        }

        log.error("stripe webook handling failed!! , " + lastException.getMessage());
    }
}

