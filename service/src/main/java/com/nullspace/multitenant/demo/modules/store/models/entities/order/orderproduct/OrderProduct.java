package com.nullspace.multitenant.demo.modules.store.models.entities.order.orderproduct;

import com.nullspace.multitenant.demo.models.entities.BaseEntity;
import com.nullspace.multitenant.demo.modules.store.models.entities.order.Order;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="order_products")
public class OrderProduct extends BaseEntity {
    @Column(name="product_sku")
    private String sku;

    @Column(name="product_name" , length=64 , nullable=false)
    private String productName;

    @Column(name="product_quantity")
    private int productQuantity;

    @Column(name="onetime_charge" , nullable=false )
    private BigDecimal oneTimeCharge;

    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @OneToMany(mappedBy = "orderProduct", cascade = CascadeType.ALL)
    private Set<OrderProductAttribute> orderAttributes = new HashSet<OrderProductAttribute>();

    @OneToMany(mappedBy = "orderProduct", cascade = CascadeType.ALL)
    private Set<OrderProductPrice> prices = new HashSet<OrderProductPrice>();

    @OneToMany(mappedBy = "orderProduct", cascade = CascadeType.ALL)
    private Set<OrderProductDownload> downloads = new HashSet<OrderProductDownload>();

    public OrderProduct() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Set<OrderProductAttribute> getOrderAttributes() {
        return orderAttributes;
    }

    public void setOrderAttributes(Set<OrderProductAttribute> orderAttributes) {
        this.orderAttributes = orderAttributes;
    }

    public Set<OrderProductPrice> getPrices() {
        return prices;
    }

    public void setPrices(Set<OrderProductPrice> prices) {
        this.prices = prices;
    }

    public Set<OrderProductDownload> getDownloads() {
        return downloads;
    }

    public void setDownloads(Set<OrderProductDownload> downloads) {
        this.downloads = downloads;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getSku() {
        return sku;
    }

    public void setOneTimeCharge(BigDecimal oneTimeCharge) {
        this.oneTimeCharge = oneTimeCharge;
    }

    public BigDecimal getOneTimeCharge() {
        return oneTimeCharge;
    }
}
