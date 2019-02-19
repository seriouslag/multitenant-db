package com.nullspace.multitenant.demo.modules.store.models.entities.order.orderproduct;

import com.nullspace.multitenant.demo.models.entities.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="order_product_prices")
public class OrderProductPrice extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "order_product_id", nullable = false)
    private OrderProduct orderProduct;

    @Column(name = "product_price_code", nullable = false , length=64 )
    private String productPriceCode;

    @Column(name = "product_price", nullable = false)
    private BigDecimal productPrice;

    @Column(name = "product_price_special")
    private BigDecimal productPriceSpecial;

    @Temporal(TemporalType.TIMESTAMP)
    @Column (name="prd_price_special_st_dt" , length=0)
    private Date productPriceSpecialStartDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column (name="prd_price_special_end_dt" , length=0)
    private Date productPriceSpecialEndDate;

    @Column(name = "default_price", nullable = false)
    private Boolean defaultPrice;

    @Column(name = "product_price_name", nullable = true)
    private String productPriceName;

    public OrderProductPrice() {
    }

    public Boolean getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(Boolean defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public String getProductPriceName() {
        return productPriceName;
    }

    public void setProductPriceName(String productPriceName) {
        this.productPriceName = productPriceName;
    }

    public OrderProduct getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(OrderProduct orderProduct) {
        this.orderProduct = orderProduct;
    }

    public void setProductPriceCode(String productPriceCode) {
        this.productPriceCode = productPriceCode;
    }

    public String getProductPriceCode() {
        return productPriceCode;
    }

    public void setProductPriceSpecialStartDate(
            Date productPriceSpecialStartDate) {
        this.productPriceSpecialStartDate = productPriceSpecialStartDate;
    }

    public Date getProductPriceSpecialStartDate() {
        return productPriceSpecialStartDate;
    }

    public void setProductPriceSpecialEndDate(Date productPriceSpecialEndDate) {
        this.productPriceSpecialEndDate = productPriceSpecialEndDate;
    }

    public Date getProductPriceSpecialEndDate() {
        return productPriceSpecialEndDate;
    }

    public void setProductPriceSpecial(BigDecimal productPriceSpecial) {
        this.productPriceSpecial = productPriceSpecial;
    }

    public BigDecimal getProductPriceSpecial() {
        return productPriceSpecial;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }
}
