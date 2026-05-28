package com.elearning.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@AllArgsConstructor
@Builder
public class PaymentSessionDto{
    private final String session_id;
    private final String session_url;
    private final Instant expireAt;
}