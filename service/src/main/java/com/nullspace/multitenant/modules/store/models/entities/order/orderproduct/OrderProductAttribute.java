package com.nullspace.multitenant.modules.store.models.entities.order.orderproduct;

import com.nullspace.multitenant.models.entities.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="order_product_attributes")
public class OrderProductAttribute extends BaseEntity {
    @Column(name= "product_attribute_price" , nullable=false , precision=15 , scale=4 )
    private BigDecimal productAttributePrice;

    @Column(name= "product_attribute_is_free" , nullable=false )
    private boolean productAttributeIsFree;

    @Column(name= "product_attribute_weight" , precision=15 , scale=4 )
    private java.math.BigDecimal productAttributeWeight;

    @ManyToOne
    @JoinColumn(name = "order_product_id", nullable = false)
    private OrderProduct orderProduct;

    @Column(name = "product_option_id", nullable = false)
    private Long productOptionId;

    @Column(name = "product_option_value_id", nullable = false)
    private Long productOptionValueId;

    @Column ( name= "product_attribute_name")
    private String productAttributeName;

    @Column ( name= "product_attribute_val_name")
    private String productAttributeValueName;

    public OrderProductAttribute() {
    }

    public boolean isProductAttributeIsFree() {
        return productAttributeIsFree;
    }

    public void setProductAttributeIsFree(boolean productAttributeIsFree) {
        this.productAttributeIsFree = productAttributeIsFree;
    }

    public java.math.BigDecimal getProductAttributeWeight() {
        return productAttributeWeight;
    }

    public void setProductAttributeWeight(
            java.math.BigDecimal productAttributeWeight) {
        this.productAttributeWeight = productAttributeWeight;
    }

    public OrderProduct getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(OrderProduct orderProduct) {
        this.orderProduct = orderProduct;
    }

    public String getProductAttributeName() {
        return productAttributeName;
    }

    public void setProductAttributeName(String productAttributeName) {
        this.productAttributeName = productAttributeName;
    }

    public String getProductAttributeValueName() {
        return productAttributeValueName;
    }

    public void setProductAttributeValueName(String productAttributeValueName) {
        this.productAttributeValueName = productAttributeValueName;
    }

    public BigDecimal getProductAttributePrice() {
        return productAttributePrice;
    }

    public void setProductAttributePrice(BigDecimal productAttributePrice) {
        this.productAttributePrice = productAttributePrice;
    }

    public Long getProductOptionId() {
        return productOptionId;
    }

    public void setProductOptionId(Long productOptionId) {
        this.productOptionId = productOptionId;
    }

    public Long getProductOptionValueId() {
        return productOptionValueId;
    }

    public void setProductOptionValueId(Long productOptionValueId) {
        this.productOptionValueId = productOptionValueId;
    }
}
