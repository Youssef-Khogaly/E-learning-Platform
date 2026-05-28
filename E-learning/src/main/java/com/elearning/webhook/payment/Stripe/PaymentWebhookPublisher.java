package com.elearning.webhook.payment.Stripe;

import com.elearning.webhook.payment.PaymentWebhookEvent;

public interface PaymentWebhookPublisher {

    void publish(PaymentWebhookEvent event);
}
