package com.elearning.External.PaymentGateWayExternal.Model;

import com.elearning.payment.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.Duration;

@Builder
public record SessionGenerationCommand(PaymentGatewayOrderModel orderModel, Duration expireAfter, String successUrl,
                                       String failUrl, PaymentMethod method) {
}
