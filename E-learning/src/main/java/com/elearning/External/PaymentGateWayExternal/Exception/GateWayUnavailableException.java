package com.elearning.External.PaymentGateWayExternal.Exception;

public class GateWayUnavailableException extends GateWayException {

    public GateWayUnavailableException(String message) {
        super(message);
    }

    public GateWayUnavailableException(Throwable cause) {
        super(cause);
    }

    public GateWayUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
