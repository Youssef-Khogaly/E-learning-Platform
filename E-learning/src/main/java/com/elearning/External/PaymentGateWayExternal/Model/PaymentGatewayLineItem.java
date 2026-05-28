package com.elearning.External.PaymentGateWayExternal.Model;

import lombok.*;

import java.util.Currency;
import java.util.List;

@AllArgsConstructor
@Getter@Setter
@NoArgsConstructor
@Builder
public class PaymentGatewayLineItem {
    private  String itemName;
    private  String itemDescription;
    private  Currency currency;
    private  int quantity;
    private long finalAmountInCents;
    private List<String> imgesUrl;
}
