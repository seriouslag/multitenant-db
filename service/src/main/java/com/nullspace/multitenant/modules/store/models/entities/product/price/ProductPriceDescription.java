package com.nullspace.multitenant.modules.store.models.entities.product.price;

import com.nullspace.multitenant.modules.store.models.entities.common.Description;

import javax.persistence.*;

@Entity
@Table(name="product_price_descriptions", uniqueConstraints={
        @UniqueConstraint(columnNames={
                "product_price_id",
        })
})
public class ProductPriceDescription extends Description {
    @ManyToOne(targetEntity = ProductPrice.class)
    @JoinColumn(name = "product_price_id", nullable = false)
    private ProductPrice productPrice;

    public ProductPriceDescription() {
    }

    public ProductPrice getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(ProductPrice productPrice) {
        this.productPrice = productPrice;
    }
}
