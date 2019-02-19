package com.nullspace.multitenant.demo.modules.store.models.entities.order.orderstatus;

import com.nullspace.multitenant.demo.models.entities.BaseEntity;
import com.nullspace.multitenant.demo.modules.store.models.entities.order.Order;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="order_status_histories")
public class OrderStatusHistory extends BaseEntity {
    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_added", nullable = false)
    private Date dateAdded;

    @Column(name = "customer_notified")
    private java.lang.Integer customerNotified;

    @Column(name = "comments")
    @Type(type = "org.hibernate.type.TextType")
    private String comments;

    public OrderStatusHistory() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getDateAdded() {
        return (Date)dateAdded.clone();
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = (Date)dateAdded.clone();
    }

    public java.lang.Integer getCustomerNotified() {
        return customerNotified;
    }

    public void setCustomerNotified(java.lang.Integer customerNotified) {
        this.customerNotified = customerNotified;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
