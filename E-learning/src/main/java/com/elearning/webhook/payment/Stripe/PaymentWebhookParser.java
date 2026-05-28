package com.elearning.webhook.payment.Stripe;

import com.elearning.webhook.payment.PaymentWebhookEvent;
import jakarta.servlet.http.HttpServletRequest;

import java.net.http.HttpRequest;

public interface PaymentWebhookParser {


    PaymentWebhookEvent parse(HttpServletRequest request , String payload);
}
