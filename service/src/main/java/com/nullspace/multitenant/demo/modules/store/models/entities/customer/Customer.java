package com.nullspace.multitenant.demo.modules.store.models.entities.customer;

import com.nullspace.multitenant.demo.models.entities.BaseEntity;
import com.nullspace.multitenant.demo.modules.store.models.entities.common.Billing;
import com.nullspace.multitenant.demo.modules.store.models.entities.common.Delivery;
import com.nullspace.multitenant.demo.modules.store.models.entities.customer.attribute.CustomerAttribute;
import com.nullspace.multitenant.demo.modules.store.models.entities.merchant.MerchantStore;
import com.nullspace.multitenant.demo.modules.store.models.entities.product.review.ProductReview;
import com.nullspace.multitenant.demo.modules.store.models.entities.user.Group;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<CustomerAttribute> attributes = new HashSet<CustomerAttribute>();

    @Column(name="customer_gender", length=1, nullable=true)
    @Enumerated(value = EnumType.STRING)
    private CustomerGender gender;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="customer_dob")
    private Date dateOfBirth;

    @Email
    @NotEmpty
    @Column(name="customer_email_address", length=96, nullable=false)
    private String emailAddress;

    @Column(name="customer_nick", length=96)
    private String nick;

    @Column(name="customer_company", length=100)
    private String company;

    @Column(name="customer_password", length=60)
    private String password;

    @Column(name="customer_anonymous")
    private boolean anonymous;

    @Column(name = "review_avg")
    private BigDecimal customerReviewAvg;

    @Column(name = "review_count")
    private Integer customerReviewCount;

    @Column(name="provider")
    private String provider;

    @OneToMany(mappedBy = "customer", targetEntity = ProductReview.class)
    private List<ProductReview> reviews = new ArrayList<ProductReview>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="merchant_id", nullable=false)
    private MerchantStore merchantStore;

    @Embedded
    private Delivery delivery = null;

    @Valid
    @Embedded
    private Billing billing = null;

    @ManyToMany(fetch=FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinTable(name = "customer_groups", joinColumns = {
            @JoinColumn(name = "customer_id", nullable = false, updatable = false) }
            ,
            inverseJoinColumns = { @JoinColumn(name = "group_id",
                    nullable = false, updatable = false) }
    )
    @Cascade({
            org.hibernate.annotations.CascadeType.DETACH,
            org.hibernate.annotations.CascadeType.LOCK,
            org.hibernate.annotations.CascadeType.REFRESH,
            org.hibernate.annotations.CascadeType.REPLICATE

    })
    private List<Group> groups = new ArrayList<Group>();

    @Transient
    private String showCustomerStateList;

    @Transient
    private String showBillingStateList;

    @Transient
    private String showDeliveryStateList;

    public Customer() {
    }

    public Date getDateOfBirth() {
        return (Date)dateOfBirth.clone();
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = (Date)dateOfBirth.clone();
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    public List<ProductReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<ProductReview> reviews) {
        this.reviews = reviews;
    }

    public void setMerchantStore(MerchantStore merchantStore) {
        this.merchantStore = merchantStore;
    }

    public MerchantStore getMerchantStore() {
        return merchantStore;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<Group> getGroups() {
        return groups;
    }
    public String getShowCustomerStateList() {
        return showCustomerStateList;
    }

    public void setShowCustomerStateList(String showCustomerStateList) {
        this.showCustomerStateList = showCustomerStateList;
    }

    public String getShowBillingStateList() {
        return showBillingStateList;
    }

    public void setShowBillingStateList(String showBillingStateList) {
        this.showBillingStateList = showBillingStateList;
    }

    public String getShowDeliveryStateList() {
        return showDeliveryStateList;
    }

    public void setShowDeliveryStateList(String showDeliveryStateList) {
        this.showDeliveryStateList = showDeliveryStateList;
    }

    public void setAttributes(Set<CustomerAttribute> attributes) {
        this.attributes = attributes;
    }

    public Set<CustomerAttribute> getAttributes() {
        return attributes;
    }

    public void setGender(CustomerGender gender) {
        this.gender = gender;
    }

    public CustomerGender getGender() {
        return gender;
    }

    public BigDecimal getCustomerReviewAvg() {
        return customerReviewAvg;
    }

    public void setCustomerReviewAvg(BigDecimal customerReviewAvg) {
        this.customerReviewAvg = customerReviewAvg;
    }

    public Integer getCustomerReviewCount() {
        return customerReviewCount;
    }

    public void setCustomerReviewCount(Integer customerReviewCount) {
        this.customerReviewCount = customerReviewCount;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}
