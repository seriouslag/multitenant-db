package com.nullspace.multitenant.modules.store.models.entities.order;

import java.util.List;

public class OrderTotalVariation {
    List<OrderTotal> variations = null;

    public List<OrderTotal> getVariations() {
        return variations;
    }

    public void setVariations(List<OrderTotal> variations) {
        this.variations = variations;
    }
}
