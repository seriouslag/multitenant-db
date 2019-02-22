package com.nullspace.multitenant.modules.store.models.entities.product;

import com.nullspace.multitenant.models.entities.BaseEntity;
import com.nullspace.multitenant.modules.store.models.entities.product.price.ProductPrice;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="product_availabilities")
public class ProductAvailability extends BaseEntity {
    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @NotNull
    @Column(name="quantity")
    private Integer productQuantity = 0;

    @Temporal(TemporalType.DATE)
    @Column(name="date_available")
    private Date productDateAvailable;

    @Column(name="status")
    private boolean productStatus = true;


    @Column(name="free_shipping")
    private boolean productIsAlwaysFreeShipping;

    @Column(name="quantity_ord_min")
    private Integer productQuantityOrderMin = 0;

    @Column(name="quantity_ord_max")
    private Integer productQuantityOrderMax = 0;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="productAvailability", cascade = CascadeType.ALL)
    private Set<ProductPrice> prices = new HashSet<>();

    @Transient
    public ProductPrice defaultPrice() {

        for(ProductPrice price : prices) {
            if(price.isDefaultPrice()) {
                return price;
            }
        }
        return new ProductPrice();
    }

    public ProductAvailability() {
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Date getProductDateAvailable() {
        return (Date)productDateAvailable.clone();
    }

    public void setProductDateAvailable(Date productDateAvailable) {
        this.productDateAvailable = productDateAvailable;
    }

    public boolean getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    public boolean getProductIsAlwaysFreeShipping() {
        return productIsAlwaysFreeShipping;
    }

    public void setProductIsAlwaysFreeShipping(boolean productIsAlwaysFreeShipping) {
        this.productIsAlwaysFreeShipping = productIsAlwaysFreeShipping;
    }

    public Integer getProductQuantityOrderMin() {
        return productQuantityOrderMin;
    }

    public void setProductQuantityOrderMin(Integer productQuantityOrderMin) {
        this.productQuantityOrderMin = productQuantityOrderMin;
    }

    public Integer getProductQuantityOrderMax() {
        return productQuantityOrderMax;
    }

    public void setProductQuantityOrderMax(Integer productQuantityOrderMax) {
        this.productQuantityOrderMax = productQuantityOrderMax;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
