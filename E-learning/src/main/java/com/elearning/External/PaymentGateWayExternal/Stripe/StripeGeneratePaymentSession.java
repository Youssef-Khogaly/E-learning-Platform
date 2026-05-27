package com.elearning.External.PaymentGateWayExternal.Stripe;



import com.elearning.External.PaymentGateWayExternal.Exception.GateWayException;
import com.elearning.External.PaymentGateWayExternal.Exception.GateWayInvalidSessionDuration;
import com.elearning.External.PaymentGateWayExternal.Interfaces.GeneratePaymentSession;
import com.elearning.External.PaymentGateWayExternal.Mappers.StripeMappers;
import com.elearning.External.PaymentGateWayExternal.Model.PaymentGatewayLineItem;
import com.elearning.External.PaymentGateWayExternal.Model.PaymentSession;
import com.elearning.External.PaymentGateWayExternal.Model.SessionGenerationCommand;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Slf4j
@Service
public class StripeGeneratePaymentSession implements GeneratePaymentSession {

    public StripeGeneratePaymentSession(@Value("${StripeApisec}")String sec) {
        Stripe.apiKey = sec ;
    }

    protected List<SessionCreateParams.LineItem> toLineItemsList(List<PaymentGatewayLineItem> items){
        return items.stream().map(StripeMappers::convertToLineItem).toList();
    }
    protected <T extends SessionGenerationCommand> SessionCreateParams createSessionPara(T command){
            return
                    SessionCreateParams.builder()
                            .addAllLineItem(toLineItemsList(command.orderModel().getItems()))
                            .setExpiresAt(Instant.now().getEpochSecond() + command.expireAfter().getSeconds())
                            .setMode(SessionCreateParams.Mode.PAYMENT)
                            .setSuccessUrl(command.successUrl())
                            .setCancelUrl(command.failUrl())
                            .putAllMetadata(command.orderModel().getMetaData())
                            .build();
    }

    @Override
    public PaymentSession generateSessionUrl(SessionGenerationCommand command) {

        if (command.expireAfter().compareTo(Duration.ofMinutes(30)) < 1){
            throw new GateWayInvalidSessionDuration("min stripe session url duration is: 30min");
        }
        SessionCreateParams params =createSessionPara(command);
        Session session = null;
        try{
            session = Session.create(params);
        }catch (StripeException e){
            log.error(this.getClass().getName()+ "msg:" +e.getMessage() + "\n" + " strip error:" + e.getStripeError());
            throw  new GateWayException(e.getMessage());
        }

        return new PaymentSession(session.getId(),session.getUrl(),session.getClientReferenceId() ,command.orderModel(),Instant.ofEpochSecond(session.getExpiresAt()));
    }

}
