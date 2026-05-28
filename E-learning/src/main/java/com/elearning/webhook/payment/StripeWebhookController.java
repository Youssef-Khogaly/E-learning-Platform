package com.elearning.webhook.payment;

import com.elearning.webhook.payment.Interfaces.PaymentWebhookParser;
import com.elearning.webhook.payment.Interfaces.PaymentWebhookPublisher;
import com.elearning.webhook.payment.Interfaces.PaymentWebhookValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.RejectedExecutionException;

@RestController("/api/webhook/stripe")
@AllArgsConstructor
@Validated
public class StripeWebhookController {

    private final PaymentWebhookPublisher paymentWebhookPublisher;
    private final PaymentWebhookParser paymentWebhookParser;
    private final PaymentWebhookValidator paymentWebhookValidator;



    @PostMapping
    ResponseEntity<Void> handleWebhook(@RequestBody @NotBlank String rawBody , HttpServletRequest request)
    {
        try{
            paymentWebhookValidator.validate(request,rawBody);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        var event = paymentWebhookParser.parse(request,rawBody);
        try {
            paymentWebhookPublisher.publish(event);
        } catch (RejectedExecutionException e) {
            ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }

        return ResponseEntity.ok().build();
    }

}
