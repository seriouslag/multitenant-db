package com.nullspace.multitenant.modules.store.models.entities.order.orderaccount;

import com.nullspace.multitenant.models.entities.BaseEntity;
import com.nullspace.multitenant.modules.store.models.entities.order.Order;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "order_accounts")
public class OrderAccount extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Temporal(TemporalType.DATE)
    @Column(name = "order_account_start_date", nullable = false, length = 0)
    private Date orderAccountStartDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "order_account_end_date", length = 0)
    private Date orderAccountEndDate;

    @Column(name = "order_account_bill_day", nullable = false)
    private Integer orderAccountBillDay;

    @OneToMany(mappedBy = "orderAccount", cascade = CascadeType.ALL)
    private Set<OrderAccountProduct> orderAccountProducts = new HashSet<OrderAccountProduct>();

    public OrderAccount() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Date getOrderAccountStartDate() {
        return (Date)orderAccountStartDate.clone();
    }

    public void setOrderAccountStartDate(Date orderAccountStartDate) {
        this.orderAccountStartDate = (Date)orderAccountStartDate.clone();
    }

    public Date getOrderAccountEndDate() {
        return (Date)orderAccountEndDate.clone();
    }

    public void setOrderAccountEndDate(Date orderAccountEndDate) {
        this.orderAccountEndDate = (Date)orderAccountEndDate.clone();
    }

    public Integer getOrderAccountBillDay() {
        return orderAccountBillDay;
    }

    public void setOrderAccountBillDay(Integer orderAccountBillDay) {
        this.orderAccountBillDay = orderAccountBillDay;
    }

    public Set<OrderAccountProduct> getOrderAccountProducts() {
        return orderAccountProducts;
    }

    public void setOrderAccountProducts(
            Set<OrderAccountProduct> orderAccountProducts) {
        this.orderAccountProducts = orderAccountProducts;
    }
}
