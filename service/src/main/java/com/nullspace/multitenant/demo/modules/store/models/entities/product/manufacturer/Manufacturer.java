package com.nullspace.multitenant.demo.modules.store.models.entities.product.manufacturer;

import com.nullspace.multitenant.demo.models.entities.BaseEntity;
import com.nullspace.multitenant.demo.modules.store.models.entities.merchant.MerchantStore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
//@EntityListeners(value = AuditListener.class)
@Table(name = "manufacturers", uniqueConstraints=
@UniqueConstraint(columnNames = {"merchant_id", "code"}) )
public class Manufacturer extends BaseEntity {
    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private Set<ManufacturerDescription> descriptions = new HashSet<>();

    @Column(name = "manufacturer_image")
    private String image;

    @Column(name="sort_order")
    private Integer order = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="merchant_id", nullable=false)
    private MerchantStore merchantStore;

    @NotEmpty
    @Column(name="code", length=100, nullable=false)
    private String code;

    public Manufacturer() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<ManufacturerDescription> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(Set<ManufacturerDescription> descriptions) {
        this.descriptions = descriptions;
    }

    public MerchantStore getMerchantStore() {
        return merchantStore;
    }

    public void setMerchantStore(MerchantStore merchantStore) {
        this.merchantStore = merchantStore;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getOrder() {
        return order;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
