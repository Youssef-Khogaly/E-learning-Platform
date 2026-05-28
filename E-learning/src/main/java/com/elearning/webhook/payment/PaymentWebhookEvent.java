package com.elearning.webhook.payment;

import com.elearning.payment.PaymentMethod;
import com.elearning.payment.PaymentProvider;
import com.elearning.payment.PaymentStatus;
import com.elearning.util.Money;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Builder
@Getter
public class PaymentWebhookEvent {

    private final String id;
    private final PaymentProvider provider;
    private final PaymentEvents event;
    private final String transactionId;
    private final String sessionId;
    private final Money totalAmount;
    private final long provider_created;
    private final Map<String,String>metaData;
}
