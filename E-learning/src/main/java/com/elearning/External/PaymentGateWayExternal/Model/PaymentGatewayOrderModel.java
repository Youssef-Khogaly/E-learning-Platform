package com.elearning.External.PaymentGateWayExternal.Model;

import lombok.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentGatewayOrderModel {
    private Map<String,String> metaData;
    private  List<PaymentGatewayLineItem> items;

}
