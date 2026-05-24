package com.elearning.payment.dto;

import com.elearning.payment.PaymentMethod;
import com.elearning.payment.PaymentStatus;
import com.elearning.util.Money;
import lombok.Builder;

import java.time.Instant;

@Builder
public record PaymentDto(long id , long usrId, Money total , PaymentMethod method
        , PaymentStatus status, String transaction_id , long paidAt , Instant createdAt,String courseTitle , long courseId) {
}
