package com.elearning.webhook.payment.Stripe;

import com.elearning.UserEnroll.UserEnrollmentJpaRepo;
import com.elearning.payment.PaymentJpaRepo;
import com.elearning.payment.PaymentProvider;
import com.elearning.payment.PaymentStatus;
import com.elearning.util.Money;
import com.elearning.webhook.payment.Interfaces.PaymentWebhookHandler;
import com.elearning.webhook.payment.Interfaces.PaymentWebhookParser;
import com.elearning.webhook.payment.Interfaces.PaymentWebhookValidator;
import com.elearning.webhook.payment.PaymentEvents;
import com.elearning.webhook.payment.PaymentWebhookEvent;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Currency;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class StripeHookImpl implements PaymentWebhookParser, PaymentWebhookValidator, PaymentWebhookHandler {

    private final String stripeHookSec;
    private final PaymentJpaRepo paymentJpaRepo;
    private final UserEnrollmentJpaRepo userEnrollmentJpaRepo;
    public StripeHookImpl(@Value("${StripeWhsec}") String stripeHookSec, PaymentJpaRepo paymentJpaRepo, UserEnrollmentJpaRepo userEnrollmentJpaRepo) {
        this.stripeHookSec = stripeHookSec;
        this.paymentJpaRepo = paymentJpaRepo;
        this.userEnrollmentJpaRepo = userEnrollmentJpaRepo;
    }

    private PaymentWebhookEvent toPaymentSucceedEvent(Event event){
        Session session = (Session) event.getDataObjectDeserializer().getObject().orElseThrow(() -> new RuntimeException("error parsing payment intent object , event Id:" + event.getId()));
        PaymentIntent intent = session.getPaymentIntentObject();
        Money amount = new Money(session.getAmountTotal(), Currency.getInstance(session.getCurrency()));
        return PaymentWebhookEvent.builder()
                .id(event.getId())
                .provider(PaymentProvider.STRIPE)
                .event(PaymentEvents.SUCCESS)
                .transactionId(intent.getLatestCharge())
                .sessionId(session.getId())
                .totalAmount(amount)
                .provider_created(event.getCreated())
                .metaData(session.getMetadata())
                .build();
    }
    private PaymentWebhookEvent toSessionExpireEvent(Event event){
        Session session = (Session) event.getDataObjectDeserializer().getObject().orElseThrow(() -> new RuntimeException("error parsing payment intent object , event Id:" + event.getId()));
        return PaymentWebhookEvent.builder()
                .id(event.getId())
                .provider(PaymentProvider.STRIPE)
                .event(PaymentEvents.SESSION_EXPIRED)
                .transactionId(null)
                .sessionId(session.getId())
                .totalAmount(null)
                .provider_created(event.getCreated())
                .metaData(session.getMetadata())
                .build();
    }
    @Override
    public PaymentWebhookEvent parse(HttpServletRequest request , String payload) {
        Event event = Event.deserializeStripeObject(payload,Event.class,Event.getGlobalResponseGetter());
        return switch (event.getType()) {
            case "checkout.session.completed" -> toPaymentSucceedEvent(event);
            case "checkout.session.expired" -> toSessionExpireEvent(event);
            default -> null;
        };
    }

    @Override
    public void validate(HttpServletRequest request , String payload) throws Exception{
        Webhook.Signature.verifyHeader(payload,request.getHeader("Stripe-Signature"),stripeHookSec, TimeUnit.MINUTES.toSeconds(5));
    }

    private void handlePaymentSuccess(PaymentWebhookEvent event)
    {
        var payment = paymentJpaRepo.findBySessionId(event.getSessionId()).orElseThrow( ()-> new RuntimeException("session id does not exists , " + event.getSessionId()));
        // duplicate event ignore
        if(payment.getStatus() == PaymentStatus.SUCCESS)
            return;

        // stripe guarantee that I collected the payment, success state should overwrite anything else
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setPaidAt(event.getProvider_created());
        payment.setTransaction_id(event.getTransactionId());
        userEnrollmentJpaRepo.enroll(payment.getUser().getId(),payment.getCourse().getId());
    }

    private void handleSessionExpire(PaymentWebhookEvent event)
    {
        var payment = paymentJpaRepo.findBySessionId(event.getSessionId()).orElseThrow( ()-> new RuntimeException("session id does not exists , " + event.getSessionId()));
        // duplicate event ignore
        // expire event should overwrite pending only!!
        if(payment.getStatus() != PaymentStatus.PENDING)
            return;
        payment.setStatus(PaymentStatus.EXPIRED);
        payment.setPaidAt(event.getProvider_created());
    }
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void handle(PaymentWebhookEvent event) {

        switch (event.getEvent()){
            case SUCCESS -> handlePaymentSuccess(event);
            case SESSION_EXPIRED -> handleSessionExpire(event);
        }
    }
}
