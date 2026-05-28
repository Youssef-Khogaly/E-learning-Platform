package com.elearning.webhook.payment.Interfaces;

public interface PaymentWebhookHandlerRetries {


    void handleWithRetries(Runnable runnable);
}
