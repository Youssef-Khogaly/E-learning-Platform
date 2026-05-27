package com.elearning.External.PaymentGateWayExternal.Interfaces;


import com.elearning.External.PaymentGateWayExternal.Model.PaymentSession;
import com.elearning.External.PaymentGateWayExternal.Model.SessionGenerationCommand;

public  interface GeneratePaymentSession {

    public <T extends SessionGenerationCommand, R extends PaymentSession> R generateSessionUrl(T command);
}
