package com.nullspace.multitenant.modules.store.models.entities.shipping;

import com.nullspace.multitenant.modules.store.models.entities.product.Product;
import com.nullspace.multitenant.modules.store.models.entities.product.price.FinalPrice;

public class ShippingProduct {
    public ShippingProduct(Product product) {
        this.product = product;
    }

    private int quantity = 1;
    private Product product;

    private FinalPrice finalPrice;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public Product getProduct() {
        return product;
    }
    public FinalPrice getFinalPrice() {
        return finalPrice;
    }
    public void setFinalPrice(FinalPrice finalPrice) {
        this.finalPrice = finalPrice;
    }
}
