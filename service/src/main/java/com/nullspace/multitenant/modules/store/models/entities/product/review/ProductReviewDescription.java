package com.nullspace.multitenant.modules.store.models.entities.product.review;

import com.nullspace.multitenant.modules.store.models.entities.common.Description;

import javax.persistence.*;

@Entity
@Table(name = "product_review_descriptions", uniqueConstraints={
        @UniqueConstraint(columnNames={
                "product_review_id",
        })
})
public class ProductReviewDescription extends Description {
    @ManyToOne(targetEntity = ProductReview.class)
    @JoinColumn(name="product_review_id")
    private ProductReview productReview;

    public ProductReviewDescription() {
    }

    public ProductReviewDescription(String name) {
        this.setName(name);
    }

    public ProductReview getProductReview() {
        return productReview;
    }

    public void setProductReview(ProductReview productReview) {
        this.productReview = productReview;
    }
}
