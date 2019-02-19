package com.nullspace.multitenant.demo.modules.store.models.entities.product.attribute;

import com.nullspace.multitenant.demo.models.entities.BaseEntity;
import com.nullspace.multitenant.demo.modules.store.models.entities.merchant.MerchantStore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="product_options", indexes = { @Index(name="prd_option_code_idx", columnList = "product_option_code")}, uniqueConstraints=
@UniqueConstraint(columnNames = {"merchant_id", "product_option_code"}))
public class ProductOption extends BaseEntity {
    @Column(name="product_option_sort_ord")
    private Integer productOptionSortOrder;

    @Column(name="product_option_type", length=10)
    private String productOptionType;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "productOption")
    private Set<ProductOptionDescription> descriptions = new HashSet<ProductOptionDescription>();

    @Transient
    private List<ProductOptionDescription> descriptionsList = new ArrayList<ProductOptionDescription>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="merchant_id", nullable=false)
    private MerchantStore merchantStore;

    @Column(name="product_option_read")
    private boolean readOnly;

    @NotEmpty
    @Pattern(regexp="^[a-zA-Z0-9_]*$")
    @Column(name="product_option_code")
    //@Index(name="prd_option_code_idx")
    private String code;

    public ProductOption() {
    }

    public Integer getProductOptionSortOrder() {
        return productOptionSortOrder;
    }

    public void setProductOptionSortOrder(Integer productOptionSortOrder) {
        this.productOptionSortOrder = productOptionSortOrder;
    }

    public String getProductOptionType() {
        return productOptionType;
    }

    public void setProductOptionType(String productOptionType) {
        this.productOptionType = productOptionType;
    }

    public Set<ProductOptionDescription> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(Set<ProductOptionDescription> descriptions) {
        this.descriptions = descriptions;
    }

    public MerchantStore getMerchantStore() {
        return merchantStore;
    }

    public void setMerchantStore(MerchantStore merchantStore) {
        this.merchantStore = merchantStore;
    }

    public void setDescriptionsList(List<ProductOptionDescription> descriptionsList) {
        this.descriptionsList = descriptionsList;
    }

    public List<ProductOptionDescription> getDescriptionsList() {
        return descriptionsList;
    }


    public List<ProductOptionDescription> getDescriptionsSettoList() {
        if(descriptionsList==null || descriptionsList.size()==0) {
            descriptionsList = new ArrayList<ProductOptionDescription>(this.getDescriptions());
        }
        return descriptionsList;

    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public boolean isReadOnly() {
        return readOnly;
    }
}
