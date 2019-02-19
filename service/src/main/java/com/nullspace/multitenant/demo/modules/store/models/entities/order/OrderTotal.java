package com.nullspace.multitenant.demo.modules.store.models.entities.order;

import com.nullspace.multitenant.demo.models.entities.BaseEntity;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="order_totals")
public class OrderTotal extends BaseEntity {
    @Column(name ="code", nullable=false)
    private String orderTotalCode;//SHIPPING, TAX

    @Column (name ="title", nullable=true)
    private String title;

    @Column (name ="text", nullable=true)
    @Type(type = "org.hibernate.type.TextType")
    private String text;

    @Column (name ="value", precision=15, scale=4, nullable=false )
    private BigDecimal value;

    @Column (name ="module", length=60 , nullable=true )
    private String module;

    @Column (name ="order_value_type")
    @Enumerated(value = EnumType.STRING)
    private OrderValueType orderValueType = OrderValueType.ONE_TIME;

    @Column (name ="order_total_type")
    @Enumerated(value = EnumType.STRING)
    private OrderTotalType orderTotalType = null;

    @Column (name ="sort_order", nullable=false)
    private int sortOrder;

    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(name = "order_id", nullable=false)
    private Order order;

    public OrderTotal() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setOrderTotalCode(String orderTotalCode) {
        this.orderTotalCode = orderTotalCode;
    }

    public String getOrderTotalCode() {
        return orderTotalCode;
    }

    public void setOrderValueType(OrderValueType orderValueType) {
        this.orderValueType = orderValueType;
    }

    public OrderValueType getOrderValueType() {
        return orderValueType;
    }

    public void setOrderTotalType(OrderTotalType orderTotalType) {
        this.orderTotalType = orderTotalType;
    }

    public OrderTotalType getOrderTotalType() {
        return orderTotalType;
    }
}
