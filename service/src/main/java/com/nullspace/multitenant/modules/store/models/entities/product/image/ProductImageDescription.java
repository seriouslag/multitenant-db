package com.nullspace.multitenant.modules.store.models.entities.product.image;

import com.nullspace.multitenant.modules.store.models.entities.common.Description;

import javax.persistence.*;

@Entity
@Table(name="product_image_descriptions", uniqueConstraints={
        @UniqueConstraint(columnNames={
                "product_image_id",
        })
})
public class ProductImageDescription extends Description {
    @ManyToOne(targetEntity = ProductImage.class)
    @JoinColumn(name = "product_image_id", nullable = false)
    private ProductImage productImage;

    @Column(name="alt_tag", length=100)
    private String altTag;

    public ProductImage getProductImage() {
        return productImage;
    }

    public void setProductImage(ProductImage productImage) {
        this.productImage = productImage;
    }

    public String getAltTag() {
        return altTag;
    }

    public void setAltTag(String altTag) {
        this.altTag = altTag;
    }
}
