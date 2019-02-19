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
@Table(name="customer_option_values",
        indexes = { @Index(name="cust_opt_val_code_idx",columnList = "customer_opt_val_code")},
        uniqueConstraints = @UniqueConstraint(columnNames = {"merchant_id", "customer_opt_val_code"}))
public class CustomerOptionValue extends BaseEntity {
    @Column(name="sort_order")
    private Integer sortOrder = 0;

    @Column(name="customer_opt_val_image")
    private String customerOptionValueImage;

    @NotEmpty
    @Pattern(regexp="^[a-zA-Z0-9_]*$")
    @Column(name="customer_opt_val_code")
    private String code;

    @Valid
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customerOptionValue")
    private Set<CustomerOptionValueDescription> descriptions = new HashSet<CustomerOptionValueDescription>();

    @Transient
    private List<CustomerOptionValueDescription> descriptionsList = new ArrayList<CustomerOptionValueDescription>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="merchant_id", nullable=false)
    private MerchantStore merchantStore;

    public CustomerOptionValue() {
    }

    public Set<CustomerOptionValueDescription> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(Set<CustomerOptionValueDescription> descriptions) {
        this.descriptions = descriptions;
    }

    public MerchantStore getMerchantStore() {
        return merchantStore;
    }

    public void setMerchantStore(MerchantStore merchantStore) {
        this.merchantStore = merchantStore;
    }

    public void setDescriptionsList(List<CustomerOptionValueDescription> descriptionsList) {
        this.descriptionsList = descriptionsList;
    }

    public List<CustomerOptionValueDescription> getDescriptionsList() {
        return descriptionsList;
    }

    public List<CustomerOptionValueDescription> getDescriptionsSettoList() {
        if(descriptionsList==null || descriptionsList.size()==0) {
            descriptionsList = new ArrayList<CustomerOptionValueDescription>(this.getDescriptions());
        }
        return descriptionsList;
    }

    //public void setImage(MultipartFile image) {
    //	this.image = image;
    //}

    //public MultipartFile getImage() {
    //	return image;
    //}

    public String getCustomerOptionValueImage() {
        return customerOptionValueImage;
    }

    public void setCustomerOptionValueImage(String customerOptionValueImage) {
        this.customerOptionValueImage = customerOptionValueImage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }
}
