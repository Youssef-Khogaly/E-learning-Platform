package com.elearning.webhook.payment.Stripe;

import jakarta.servlet.http.HttpServletRequest;

public interface PaymentWebhookValidator {


    void validate(HttpServletRequest request , String payload) throws Exception;
}
