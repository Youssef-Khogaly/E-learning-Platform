package com.elearning.External.PaymentGateWayExternal.Exception;

public class GateWayConnectionException extends GateWayException {

    public GateWayConnectionException(String message) {
        super(message);
    }

    public GateWayConnectionException(Throwable cause) {
        super(cause);
    }

    public GateWayConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
