package com.nullspace.multitenant.demo.modules.store.models.entities.order;

import com.nullspace.multitenant.demo.modules.store.models.entities.shipping.ShippingSummary;
import com.nullspace.multitenant.demo.modules.store.models.entities.shoppingcart.ShoppingCartItem;

import java.util.ArrayList;
import java.util.List;

public class OrderSummary {
    private OrderSummaryType orderSummaryType = OrderSummaryType.ORDERTOTAL;
    private static final long serialVersionUID = 1L;
    private ShippingSummary shippingSummary;
    private List<ShoppingCartItem> products = new ArrayList<ShoppingCartItem>();

    public void setProducts(List<ShoppingCartItem> products) {
        this.products = products;
    }
    public List<ShoppingCartItem> getProducts() {
        return products;
    }
    public void setShippingSummary(ShippingSummary shippingSummary) {
        this.shippingSummary = shippingSummary;
    }
    public ShippingSummary getShippingSummary() {
        return shippingSummary;
    }
    public OrderSummaryType getOrderSummaryType() {
        return orderSummaryType;
    }
    public void setOrderSummaryType(OrderSummaryType orderSummaryType) {
        this.orderSummaryType = orderSummaryType;
    }
}
