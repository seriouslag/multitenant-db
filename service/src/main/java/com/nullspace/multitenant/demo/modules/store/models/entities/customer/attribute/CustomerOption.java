package com.nullspace.multitenant.demo.modules.store.models.entities.customer.attribute;

import com.nullspace.multitenant.demo.models.entities.BaseEntity;
import com.nullspace.multitenant.demo.modules.store.models.entities.merchant.MerchantStore;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="customer_options", indexes = { @Index(name="cust_opt_code_idx", columnList = "CUSTOMER_OPT_CODE")}, uniqueConstraints=
@UniqueConstraint(columnNames = {"merchant_id", "customer_opt_code"}))
public class CustomerOption extends BaseEntity {
    @Column(name="sort_order")
    private Integer sortOrder = 0;

    @Column(name="customer_option_type", length=10)
    private String customerOptionType;

    @NotEmpty
    @Pattern(regexp="^[a-zA-Z0-9_]*$")
    @Column(name="customer_opt_code")
    //@Index(name="CUST_OPT_CODE_IDX")
    private String code;

    @Column(name="customer_opt_active")
    private boolean active;

    @Column(name="CUSTOMER_OPT_PUBLIC")
    private boolean publicOption;

    @Valid
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customerOption")
    private Set<CustomerOptionDescription> descriptions = new HashSet<CustomerOptionDescription>();

    @Transient
    private List<CustomerOptionDescription> descriptionsList = new ArrayList<CustomerOptionDescription>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="merchant_id", nullable=false)
    private MerchantStore merchantStore;

    public CustomerOption() {
    }

    public Set<CustomerOptionDescription> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(Set<CustomerOptionDescription> descriptions) {
        this.descriptions = descriptions;
    }

    public MerchantStore getMerchantStore() {
        return merchantStore;
    }

    public void setMerchantStore(MerchantStore merchantStore) {
        this.merchantStore = merchantStore;
    }

    public void setDescriptionsList(List<CustomerOptionDescription> descriptionsList) {
        this.descriptionsList = descriptionsList;
    }

    public List<CustomerOptionDescription> getDescriptionsList() {
        return descriptionsList;
    }

    public List<CustomerOptionDescription> getDescriptionsSettoList() {
        if(descriptionsList==null || descriptionsList.size()==0) {
            descriptionsList = new ArrayList<CustomerOptionDescription>(this.getDescriptions());
        }
        return descriptionsList;
    }

    public String getCustomerOptionType() {
        return customerOptionType;
    }

    public void setCustomerOptionType(String customerOptionType) {
        this.customerOptionType = customerOptionType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isPublicOption() {
        return publicOption;
    }

    public void setPublicOption(boolean publicOption) {
        this.publicOption = publicOption;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }
}
