package com.elearning.payment.mappers;

import com.elearning.payment.Payment;
import com.elearning.payment.dto.PaymentDto;
import org.springframework.stereotype.Component;

@Component
public class PaymentDtoMapper {


    public PaymentDto from(Payment payment)
    {
        return PaymentDto.builder()
                .id(payment.getId())
                .usrId(payment.getUser().getId())
                .total(payment.getTotalAmount())
                .method(payment.getMethod())
                .status(payment.getStatus())
                .transaction_id(payment.getTransaction_id())
                .paidAt(payment.getPaidAt())
                .createdAt(payment.getCreatedAt())
                .courseTitle(payment.getCourse().getTitle())
                .courseId(payment.getCourse().getId())
                .build();
    }
}
