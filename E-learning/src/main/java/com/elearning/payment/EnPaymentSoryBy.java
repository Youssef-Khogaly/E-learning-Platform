package com.elearning.payment;

public enum EnPaymentSoryBy {
    CREATEDAT("createdAt");

    private String val;

    EnPaymentSoryBy(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val;
    }
}
