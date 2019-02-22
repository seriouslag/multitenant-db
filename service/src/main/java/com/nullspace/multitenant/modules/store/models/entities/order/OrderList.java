package com.nullspace.multitenant.modules.store.models.entities.order;

import com.nullspace.multitenant.modules.store.models.entities.common.EntityList;

import java.util.List;

public class OrderList extends EntityList {
    private List<Order> orders;

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
