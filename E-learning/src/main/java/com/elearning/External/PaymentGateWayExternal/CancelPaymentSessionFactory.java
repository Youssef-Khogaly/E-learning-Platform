package com.elearning.External.PaymentGateWayExternal;


import com.elearning.External.PaymentGateWayExternal.Interfaces.CancelSession;
import com.elearning.External.PaymentGateWayExternal.Stripe.StripeCancelSession;
import com.elearning.payment.PaymentMethod;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CancelPaymentSessionFactory {
    private final ApplicationContext context;


    public CancelSession getCancelSessionStratigy(PaymentMethod method){
        return  switch (method){
            case STRIPE -> context.getBean(StripeCancelSession.class);
            default -> throw new IllegalArgumentException("illegal payment method");
        };

    }
}
