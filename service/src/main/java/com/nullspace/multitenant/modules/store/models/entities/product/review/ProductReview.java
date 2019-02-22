package com.nullspace.multitenant.modules.store.models.entities.product.review;

import com.nullspace.multitenant.models.entities.BaseEntity;
import com.nullspace.multitenant.modules.store.models.entities.customer.Customer;
import com.nullspace.multitenant.modules.store.models.entities.product.Product;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
// @EntityListeners(value = AuditListener.class)
@Table(name = "product_reviews", uniqueConstraints={
        @UniqueConstraint(columnNames={
                "customers_id",
                "product_id"
        })
})
public class ProductReview extends BaseEntity {
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
    @JoinColumn(name="product_id")
    private Product product;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "productReview")
    private Set<ProductReviewDescription> descriptions = new HashSet<>();

    public ProductReview() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Set<ProductReviewDescription> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(Set<ProductReviewDescription> descriptions) {
        this.descriptions = descriptions;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }
}
