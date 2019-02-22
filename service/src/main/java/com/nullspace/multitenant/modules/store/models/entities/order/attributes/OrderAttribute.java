package com.nullspace.multitenant.modules.store.models.entities.order.attributes;

import com.nullspace.multitenant.models.entities.BaseEntity;
import com.nullspace.multitenant.modules.store.models.entities.order.Order;

import javax.persistence.*;

@Entity
@Table(name="order_attributes")
public class OrderAttribute extends BaseEntity {
    @Column(name ="identifier", nullable=false)
    private String key;

    @Column (name ="value", nullable=false)
    private String value;

    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(name = "order_id", nullable=false)
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
