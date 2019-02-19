package com.nullspace.multitenant.demo.modules.store.models.entities.product.price;

import com.nullspace.multitenant.demo.models.entities.BaseEntity;
import com.nullspace.multitenant.demo.modules.store.models.entities.product.ProductAvailability;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product_prices")
public class ProductPrice extends BaseEntity {
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productPrice", cascade = CascadeType.ALL)
    private Set<ProductPriceDescription> descriptions = new HashSet<ProductPriceDescription>();

    @Column(name = "product_price_amount", nullable=false)
    private BigDecimal productPriceAmount = new BigDecimal(0);

    @Column(name = "product_price_type", length=20)
    @Enumerated(value = EnumType.STRING)
    private ProductPriceType productPriceType = ProductPriceType.ONE_TIME;

    @Column(name = "default_price")
    private boolean defaultPrice = false;

    @Temporal(TemporalType.DATE)
    @Column(name = "product_price_special_st_date")
    private Date productPriceSpecialStartDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "product_price_special_end_date")
    private Date productPriceSpecialEndDate;

    @Column(name = "product_price_special_amount")
    private BigDecimal productPriceSpecialAmount;


    @ManyToOne(targetEntity = ProductAvailability.class)
    @JoinColumn(name = "product_avail_id", nullable = false)
    private ProductAvailability productAvailability;

    public ProductPrice() {
    }

    public BigDecimal getProductPriceAmount() {
        return productPriceAmount;
    }

    public void setProductPriceAmount(BigDecimal productPriceAmount) {
        this.productPriceAmount = productPriceAmount;
    }

    public Date getProductPriceSpecialStartDate() {
        return (Date)productPriceSpecialStartDate.clone();
    }

    public void setProductPriceSpecialStartDate(
            Date productPriceSpecialStartDate) {
        this.productPriceSpecialStartDate = (Date)productPriceSpecialStartDate.clone();
    }

    public Date getProductPriceSpecialEndDate() {
        return (Date)productPriceSpecialEndDate.clone();
    }

    public void setProductPriceSpecialEndDate(Date productPriceSpecialEndDate) {
        this.productPriceSpecialEndDate = (Date)productPriceSpecialEndDate.clone();
    }

    public BigDecimal getProductPriceSpecialAmount() {
        return productPriceSpecialAmount;
    }

    public void setProductPriceSpecialAmount(
            BigDecimal productPriceSpecialAmount) {
        this.productPriceSpecialAmount = productPriceSpecialAmount;
    }

    public Set<ProductPriceDescription> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(Set<ProductPriceDescription> descriptions) {
        this.descriptions = descriptions;
    }

    public boolean isDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(boolean defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public void setProductAvailability(ProductAvailability productAvailability) {
        this.productAvailability = productAvailability;
    }

    public ProductAvailability getProductAvailability() {
        return productAvailability;
    }

    public void setProductPriceType(ProductPriceType productPriceType) {
        this.productPriceType = productPriceType;
    }

    public ProductPriceType getProductPriceType() {
        return productPriceType;
    }
}
