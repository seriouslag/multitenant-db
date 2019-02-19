package com.nullspace.multitenant.demo.modules.store.models.entities.order.orderaccount;

import com.nullspace.multitenant.demo.models.entities.BaseEntity;
import com.nullspace.multitenant.demo.modules.store.models.entities.order.orderproduct.OrderProduct;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="order_account_products")
public class OrderAccountProduct extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "order_account_id" , nullable=false)
    private OrderAccount orderAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_product_id" , nullable=false)
    private OrderProduct orderProduct;

    @Temporal(TemporalType.DATE)
    @Column (name="order_account_product_st_dt" , length=0 , nullable=false)
    private Date orderAccountProductStartDate;

    @Temporal(TemporalType.DATE)
    @Column (name="order_account_product_end_dt", length=0)
    private Date orderAccountProductEndDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column (name="order_account_product_eot"  , length=0 )
    private Date orderAccountProductEot;

    @Temporal(TemporalType.DATE)
    @Column (name="order_account_product_accnt_dt"  , length=0 )
    private Date orderAccountProductAccountedDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column (name="order_account_product_l_st_dt"  , length=0 )
    private Date orderAccountProductLastStatusDate;

    @Column (name="order_account_product_l_trx_st" , nullable=false )
    private Integer orderAccountProductLastTransactionStatus;

    @Column (name="order_account_product_pm_fr_ty" , nullable=false )
    private Integer orderAccountProductPaymentFrequencyType;

    @Column (name="order_account_product_status" , nullable=false )
    private Integer orderAccountProductStatus;

    public OrderAccountProduct() {
    }

    public OrderAccount getOrderAccount() {
        return orderAccount;
    }

    public void setOrderAccount(OrderAccount orderAccount) {
        this.orderAccount = orderAccount;
    }

    public OrderProduct getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(OrderProduct orderProduct) {
        this.orderProduct = orderProduct;
    }

    public Date getOrderAccountProductStartDate() {
        return (Date)orderAccountProductStartDate.clone();
    }

    public void setOrderAccountProductStartDate(Date orderAccountProductStartDate) {
        this.orderAccountProductStartDate = (Date)orderAccountProductStartDate.clone();
    }

    public Date getOrderAccountProductEndDate() {
        return (Date)orderAccountProductEndDate.clone();
    }

    public void setOrderAccountProductEndDate(Date orderAccountProductEndDate) {
        this.orderAccountProductEndDate = (Date)orderAccountProductEndDate.clone();
    }

    public Date getOrderAccountProductEot() {
        return (Date)orderAccountProductEot.clone();
    }

    public void setOrderAccountProductEot(Date orderAccountProductEot) {
        this.orderAccountProductEot = (Date)orderAccountProductEot.clone();
    }

    public Date getOrderAccountProductAccountedDate() {
        return (Date)orderAccountProductAccountedDate.clone();
    }

    public void setOrderAccountProductAccountedDate(
            Date orderAccountProductAccountedDate) {
        this.orderAccountProductAccountedDate = (Date)orderAccountProductAccountedDate.clone();
    }

    public Date getOrderAccountProductLastStatusDate() {
        return (Date)orderAccountProductLastStatusDate.clone();
    }

    public void setOrderAccountProductLastStatusDate(
            Date orderAccountProductLastStatusDate) {
        this.orderAccountProductLastStatusDate = (Date)orderAccountProductLastStatusDate.clone();
    }

    public Integer getOrderAccountProductLastTransactionStatus() {
        return orderAccountProductLastTransactionStatus;
    }

    public void setOrderAccountProductLastTransactionStatus(
            Integer orderAccountProductLastTransactionStatus) {
        this.orderAccountProductLastTransactionStatus = orderAccountProductLastTransactionStatus;
    }

    public Integer getOrderAccountProductPaymentFrequencyType() {
        return orderAccountProductPaymentFrequencyType;
    }

    public void setOrderAccountProductPaymentFrequencyType(
            Integer orderAccountProductPaymentFrequencyType) {
        this.orderAccountProductPaymentFrequencyType = orderAccountProductPaymentFrequencyType;
    }

    public Integer getOrderAccountProductStatus() {
        return orderAccountProductStatus;
    }

    public void setOrderAccountProductStatus(Integer orderAccountProductStatus) {
        this.orderAccountProductStatus = orderAccountProductStatus;
    }
}
