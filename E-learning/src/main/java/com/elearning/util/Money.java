package com.elearning.util;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Currency;

@Getter
@Embeddable
@NoArgsConstructor(force = true)
public class Money {

    private final long priceInCents;
    private final Currency currency;
    public static final Currency defaultCurrency = Currency.getInstance("USD");
    public Money(long minor_unit_amount, Currency currency) {
        this.priceInCents = minor_unit_amount;
        this.currency = currency;
    }

    public Money(Money money) {
        this.priceInCents = money.getPriceInCents();
        this.currency = money.getCurrency();
    }
}
