package com.nullspace.multitenant.demo.modules.store.models.entities.customer.Review;

import com.nullspace.multitenant.demo.modules.store.models.entities.common.Description;

import javax.persistence.*;

@Entity
@Table(name = "customer_review_descriptions", uniqueConstraints={
        @UniqueConstraint(columnNames={
                "customer_review_id",
        })
})
public class CustomerReviewDescription extends Description {
    @ManyToOne(targetEntity = CustomerReview.class)
    @JoinColumn(name="customer_review_id")
    private CustomerReview customerReview;

    public CustomerReview getCustomerReview() {
        return customerReview;
    }

    public void setCustomerReview(CustomerReview customerReview) {
        this.customerReview = customerReview;
    }

    public CustomerReviewDescription() {
    }

    public CustomerReviewDescription(String name) {
        this.setName(name);
    }
}
