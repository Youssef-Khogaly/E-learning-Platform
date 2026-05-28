package com.elearning.External.PaymentGateWayExternal.Exception;

public class GateWayException extends RuntimeException {

    public GateWayException(String message) {
        super(message);
    }

    public GateWayException(Throwable cause) {
        super(cause);
    }

    public GateWayException(String message, Throwable cause) {
        super(message, cause);
    }
}
