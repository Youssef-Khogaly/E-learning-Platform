package com.elearning.webhook.payment.Stripe;

import com.elearning.webhook.payment.PaymentWebhookEvent;
import jakarta.servlet.http.HttpServletRequest;

public interface PaymentWebhookHandler {
    public void handle(PaymentWebhookEvent event);
}
