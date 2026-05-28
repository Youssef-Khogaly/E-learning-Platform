package com.elearning.External.PaymentGateWayExternal.Exception;

public class GateWayInvalidSessionDuration extends GateWayException {
    public GateWayInvalidSessionDuration(String message) {
        super(message);
    }

    public GateWayInvalidSessionDuration(Throwable cause) {
        super(cause);
    }

    public GateWayInvalidSessionDuration(String message, Throwable cause) {
        super(message, cause);
    }
}
