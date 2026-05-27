package com.elearning.External.PaymentGateWayExternal.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentGatewayOrderModel {
    private Map<String,String> metaData;
    private  List<PaymentGatewayLineItem> items;

}
