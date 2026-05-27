package com.elearning.External.PaymentGateWayExternal;



import com.elearning.External.PaymentGateWayExternal.Interfaces.GeneratePaymentSession;
import com.elearning.External.PaymentGateWayExternal.Stripe.StripeGeneratePaymentSession;
import com.elearning.payment.PaymentMethod;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GeneratePaymentSessionFactory {

    private final ApplicationContext context;
    public GeneratePaymentSession getStratigy(PaymentMethod method){
        return  switch (method){
            case STRIPE -> context.getBean(StripeGeneratePaymentSession.class);
            default -> throw new IllegalArgumentException("illegal payment method");
        };

    }

}
