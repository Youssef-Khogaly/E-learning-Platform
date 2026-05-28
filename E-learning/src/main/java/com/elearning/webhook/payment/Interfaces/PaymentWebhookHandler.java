package com.elearning.webhook.payment.Interfaces;

import com.elearning.webhook.payment.PaymentWebhookEvent;

public interface PaymentWebhookHandler {
    public void handle(PaymentWebhookEvent event);
}
