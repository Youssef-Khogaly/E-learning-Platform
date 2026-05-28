package com.elearning.External.PaymentGateWayExternal.Exception;

public class GateWayRateLimitException extends GateWayException {


    GateWayRateLimitException(String message) {
        super(message);
    }

    public GateWayRateLimitException(String message, Throwable cause) {
        super(message, cause);
    }

    public GateWayRateLimitException(Throwable cause) {
        super(cause);
    }
}
