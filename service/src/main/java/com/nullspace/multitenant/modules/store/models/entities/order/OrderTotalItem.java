package com.nullspace.multitenant.modules.store.models.entities.order;

import java.math.BigDecimal;

public class OrderTotalItem {
    private BigDecimal itemPrice;
    private String itemCode;
    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }
    public BigDecimal getItemPrice() {
        return itemPrice;
    }
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
    public String getItemCode() {
        return itemCode;
    }
}
