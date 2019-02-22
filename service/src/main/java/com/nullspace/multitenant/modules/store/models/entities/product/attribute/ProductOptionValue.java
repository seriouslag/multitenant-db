package com.nullspace.multitenant.modules.store.models.entities.product.attribute;

import com.nullspace.multitenant.models.entities.BaseEntity;
import com.nullspace.multitenant.modules.store.models.entities.merchant.MerchantStore;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="product_option_values", indexes = { @Index(name="prd_option_val_code_idx", columnList = "product_option_val_code")}, uniqueConstraints=
@UniqueConstraint(columnNames = {"merchant_id", "product_option_val_code"}))
public class ProductOptionValue extends BaseEntity {
    @Column(name="product_opt_val_sort_ord")
    private Integer productOptionValueSortOrder;

    @Column(name="product_opt_val_image")
    private String productOptionValueImage;

    @Column(name="product_opt_for_disp")
    private boolean productOptionDisplayOnly=false;

    @NotEmpty
    @Pattern(regexp="^[a-zA-Z0-9_]*$")
    @Column(name="PRODUCT_OPTION_VAL_CODE")
    private String code;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "productOptionValue")
    private Set<ProductOptionValueDescription> descriptions = new HashSet<>();

    @Transient
    private MultipartFile image = null;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    @Transient
    private List<ProductOptionValueDescription> descriptionsList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="merchant_id", nullable=false)
    private MerchantStore merchantStore;

    public ProductOptionValue() {
    }

    public Integer getProductOptionValueSortOrder() {
        return productOptionValueSortOrder;
    }

    public void setProductOptionValueSortOrder(Integer productOptionValueSortOrder) {
        this.productOptionValueSortOrder = productOptionValueSortOrder;
    }

    public String getProductOptionValueImage() {
        return productOptionValueImage;
    }

    public void setProductOptionValueImage(String productOptionValueImage) {
        this.productOptionValueImage = productOptionValueImage;
    }

    public Set<ProductOptionValueDescription> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(Set<ProductOptionValueDescription> descriptions) {
        this.descriptions = descriptions;
    }

    public MerchantStore getMerchantStore() {
        return merchantStore;
    }

    public void setMerchantStore(MerchantStore merchantStore) {
        this.merchantStore = merchantStore;
    }

    public void setDescriptionsList(List<ProductOptionValueDescription> descriptionsList) {
        this.descriptionsList = descriptionsList;
    }

    public List<ProductOptionValueDescription> getDescriptionsList() {
        return descriptionsList;
    }

    public List<ProductOptionValueDescription> getDescriptionsSettoList() {
        if(descriptionsList==null || descriptionsList.size()==0) {
            descriptionsList = new ArrayList<>(this.getDescriptions());
        }
        return descriptionsList;
    }

    public boolean isProductOptionDisplayOnly() {
        return productOptionDisplayOnly;
    }

    public void setProductOptionDisplayOnly(boolean productOptionDisplayOnly) {
        this.productOptionDisplayOnly = productOptionDisplayOnly;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
