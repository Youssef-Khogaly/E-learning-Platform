package com.elearning.util;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Currency;

@Getter
@Embeddable
@NoArgsConstructor(force = true)
public class Money {

    private final long amount;
    private final Currency currency;

    public Money(long minor_unit_amount, Currency currency) {
        this.amount = minor_unit_amount;
        this.currency = currency;
    }
}
