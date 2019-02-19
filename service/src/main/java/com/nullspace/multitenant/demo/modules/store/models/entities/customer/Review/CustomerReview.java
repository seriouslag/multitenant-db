package com.nullspace.multitenant.demo.modules.store.models.entities.customer.Review;

import com.nullspace.multitenant.demo.models.entities.BaseEntity;
import com.nullspace.multitenant.demo.modules.store.models.entities.customer.Customer;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
//@EntityListeners(value = AuditListener.class)
@Table(name = "customer_reviews", uniqueConstraints={
        @UniqueConstraint(columnNames={
                "customers_id",
                "reviewed_customer_id"
        })
}
)
public class CustomerReview extends BaseEntity {
    @Column(name = "reviews_rating")
    private Double reviewRating;

    @Column(name = "reviews_read")
    private Long reviewRead;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "review_date")
    private Date reviewDate;

    @Column(name = "status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name="customers_id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name="reviewed_customer_id")
    private Customer reviewedCustomer;

    public Customer getReviewedCustomer() {
        return reviewedCustomer;
    }

    public void setReviewedCustomer(Customer reviewedCustomer) {
        this.reviewedCustomer = reviewedCustomer;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customerReview")
    private Set<CustomerReviewDescription> descriptions = new HashSet<CustomerReviewDescription>();

    public CustomerReview() {
    }

    public Double getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(Double reviewRating) {
        this.reviewRating = reviewRating;
    }

    public Long getReviewRead() {
        return reviewRead;
    }

    public void setReviewRead(Long reviewRead) {
        this.reviewRead = reviewRead;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public Set<CustomerReviewDescription> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(Set<CustomerReviewDescription> descriptions) {
        this.descriptions = descriptions;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }
}
