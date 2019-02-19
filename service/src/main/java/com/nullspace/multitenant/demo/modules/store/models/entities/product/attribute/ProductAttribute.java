package com.nullspace.multitenant.demo.modules.store.models.entities.product.attribute;


import com.nullspace.multitenant.demo.models.entities.BaseEntity;
import com.nullspace.multitenant.demo.modules.store.models.entities.product.Product;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="product_attributes", uniqueConstraints={
                @UniqueConstraint(columnNames={
                        "option_id",
                        "option_value_id",
                        "product_id"
            })
})
public class ProductAttribute extends BaseEntity {
    @Column(name="product_attribute_price")
    private BigDecimal productAttributePrice;

    @Column(name="product_attribute_sort_ord")
    private Integer productOptionSortOrder;

    @Column(name="product_attribute_free")
    private boolean productAttributeIsFree;

    @Column(name="product_attribute_weight")
    private BigDecimal productAttributeWeight;

    @Column(name="product_attribute_default")
    private boolean attributeDefault=false;

    @Column(name="product_attribute_required")
    private boolean attributeRequired=false;

    /**
     * a read only attribute is considered as a core attribute addition
     */
    @Column(name="product_attribute_for_disp")
    private boolean attributeDisplayOnly=false;

    @Column(name="product_attribute_discounted")
    private boolean attributeDiscounted=false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="option_id", nullable=false)
    private ProductOption productOption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="option_value_id", nullable=false)
    private ProductOptionValue productOptionValue;

    /**
     * This transient object property
     * is a utility used only to submit from a free text
     */
    @Transient
    private String attributePrice = "0";

    /**
     * This transient object property
     * is a utility used only to submit from a free text
     */
    @Transient
    private String attributeSortOrder = "0";

    /**
     * This transient object property
     * is a utility used only to submit from a free text
     */
    @Transient
    private String attributeAdditionalWeight = "0";

    public String getAttributePrice() {
        return attributePrice;
    }

    public void setAttributePrice(String attributePrice) {
        this.attributePrice = attributePrice;
    }

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public ProductAttribute() {
    }

    public Integer getProductOptionSortOrder() {
        return productOptionSortOrder;
    }

    public void setProductOptionSortOrder(Integer productOptionSortOrder) {
        this.productOptionSortOrder = productOptionSortOrder;
    }

    public boolean getProductAttributeIsFree() {
        return productAttributeIsFree;
    }

    public void setProductAttributeIsFree(boolean productAttributeIsFree) {
        this.productAttributeIsFree = productAttributeIsFree;
    }

    public BigDecimal getProductAttributeWeight() {
        return productAttributeWeight;
    }

    public void setProductAttributeWeight(BigDecimal productAttributeWeight) {
        this.productAttributeWeight = productAttributeWeight;
    }

    public boolean getAttributeDefault() {
        return attributeDefault;
    }

    public void setAttributeDefault(boolean attributeDefault) {
        this.attributeDefault = attributeDefault;
    }

    public boolean getAttributeRequired() {
        return attributeRequired;
    }

    public void setAttributeRequired(boolean attributeRequired) {
        this.attributeRequired = attributeRequired;
    }

    public boolean getAttributeDisplayOnly() {
        return attributeDisplayOnly;
    }

    public void setAttributeDisplayOnly(boolean attributeDisplayOnly) {
        this.attributeDisplayOnly = attributeDisplayOnly;
    }

    public boolean getAttributeDiscounted() {
        return attributeDiscounted;
    }

    public void setAttributeDiscounted(boolean attributeDiscounted) {
        this.attributeDiscounted = attributeDiscounted;
    }

    public ProductOption getProductOption() {
        return productOption;
    }

    public void setProductOption(ProductOption productOption) {
        this.productOption = productOption;
    }

    public ProductOptionValue getProductOptionValue() {
        return productOptionValue;
    }

    public void setProductOptionValue(ProductOptionValue productOptionValue) {
        this.productOptionValue = productOptionValue;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public String getAttributeSortOrder() {
        return attributeSortOrder;
    }

    public void setAttributeSortOrder(String attributeSortOrder) {
        this.attributeSortOrder = attributeSortOrder;
    }

    public String getAttributeAdditionalWeight() {
        return attributeAdditionalWeight;
    }

    public void setAttributeAdditionalWeight(String attributeAdditionalWeight) {
        this.attributeAdditionalWeight = attributeAdditionalWeight;
    }

    public BigDecimal getProductAttributePrice() {
        return productAttributePrice;
    }

    public void setProductAttributePrice(BigDecimal productAttributePrice) {
        this.productAttributePrice = productAttributePrice;
    }
}
