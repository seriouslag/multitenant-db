package com.nullspace.multitenant.demo.modules.store.models.entities.shipping;

import com.nullspace.multitenant.demo.modules.store.models.entities.common.Delivery;

import java.math.BigDecimal;

public class ShippingSummary {
    private BigDecimal shipping;
    private BigDecimal handling;
    private String shippingModule;
    private String shippingOption;
    private String shippingOptionCode;
    private boolean freeShipping;
    private boolean taxOnShipping;
    private Delivery deliveryAddress;

    public BigDecimal getShipping() {
        return shipping;
    }
    public void setShipping(BigDecimal shipping) {
        this.shipping = shipping;
    }
    public BigDecimal getHandling() {
        return handling;
    }
    public void setHandling(BigDecimal handling) {
        this.handling = handling;
    }
    public String getShippingModule() {
        return shippingModule;
    }
    public void setShippingModule(String shippingModule) {
        this.shippingModule = shippingModule;
    }
    public String getShippingOption() {
        return shippingOption;
    }
    public void setShippingOption(String shippingOption) {
        this.shippingOption = shippingOption;
    }
    public boolean isFreeShipping() {
        return freeShipping;
    }
    public void setFreeShipping(boolean freeShipping) {
        this.freeShipping = freeShipping;
    }
    public boolean isTaxOnShipping() {
        return taxOnShipping;
    }
    public void setTaxOnShipping(boolean taxOnShipping) {
        this.taxOnShipping = taxOnShipping;
    }
    public Delivery getDeliveryAddress() {
        return deliveryAddress;
    }
    public void setDeliveryAddress(Delivery deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
    public String getShippingOptionCode() {
        return shippingOptionCode;
    }
    public void setShippingOptionCode(String shippingOptionCode) {
        this.shippingOptionCode = shippingOptionCode;
    }
}
