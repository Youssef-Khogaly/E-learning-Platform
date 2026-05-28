package com.elearning.webhook.payment.Interfaces;

import com.elearning.webhook.payment.PaymentWebhookEvent;

import java.util.concurrent.RejectedExecutionException;

public interface PaymentWebhookPublisher {

    void publish(PaymentWebhookEvent event) throws RejectedExecutionException;
}
