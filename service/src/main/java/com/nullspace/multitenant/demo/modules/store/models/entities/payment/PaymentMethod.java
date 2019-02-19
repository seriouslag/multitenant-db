package com.nullspace.multitenant.demo.modules.store.models.entities.payment;

import com.nullspace.multitenant.demo.modules.store.models.entities.system.IntegrationConfiguration;
import com.nullspace.multitenant.demo.modules.store.models.entities.system.IntegrationModule;

public class PaymentMethod {
    private static final long serialVersionUID = 1L;
    private String paymentMethodCode;
    private PaymentType paymentType;
    private boolean defaultSelected;
    private IntegrationModule module;
    private IntegrationConfiguration informations;

    public PaymentType getPaymentType() {
        return paymentType;
    }
    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
    public String getPaymentMethodCode() {
        return paymentMethodCode;
    }
    public void setPaymentMethodCode(String paymentMethodCode) {
        this.paymentMethodCode = paymentMethodCode;
    }
    public boolean isDefaultSelected() {
        return defaultSelected;
    }
    public void setDefaultSelected(boolean defaultSelected) {
        this.defaultSelected = defaultSelected;
    }
    public IntegrationModule getModule() {
        return module;
    }
    public void setModule(IntegrationModule module) {
        this.module = module;
    }
    public IntegrationConfiguration getInformations() {
        return informations;
    }
    public void setInformations(IntegrationConfiguration informations) {
        this.informations = informations;
    }
}
