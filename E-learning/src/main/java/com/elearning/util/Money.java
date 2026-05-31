package com.elearning.util;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Currency;

@Getter
@Embeddable
@NoArgsConstructor(force = true)
public class Money {

    @Column(name = "price")
    @Positive
    private final Long priceInCents;
    @NotNull
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
