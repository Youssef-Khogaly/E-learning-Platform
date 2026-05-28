package com.elearning.webhook.payment.Interfaces;

import com.elearning.webhook.payment.PaymentWebhookEvent;
import jakarta.servlet.http.HttpServletRequest;

public interface PaymentWebhookParser {


    PaymentWebhookEvent parse(HttpServletRequest request , String payload);
}
