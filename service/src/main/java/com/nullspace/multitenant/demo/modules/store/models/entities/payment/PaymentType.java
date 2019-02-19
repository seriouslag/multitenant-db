package com.nullspace.multitenant.demo.modules.store.models.entities.payment;

public enum PaymentType {
    CREDITCARD("creditcard"),
    FREE("creditcard"),
    COD("creditcard"),
    MONEYORDER("creditcard"),
    PAYPAL("creditcard"),
    STRIPE("creditcard"),
    WEPAY("creditcard"),
    ;

    private String paymentType;

    PaymentType(String type) {
        paymentType = type;
    }

    public static PaymentType fromString(String text) {
        if (text != null) {
            for (PaymentType b : PaymentType.values()) {
                String paymentType = text.toUpperCase();
                if (paymentType.equalsIgnoreCase(b.name())) {
                    return b;
                }
            }
        }
        return null;
    }
}