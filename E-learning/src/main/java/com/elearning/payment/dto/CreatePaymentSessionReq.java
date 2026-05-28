package com.elearning.payment.dto;

import com.elearning.payment.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public record CreatePaymentSessionReq(@Positive Long courseId, @NotNull PaymentMethod method) {
}
