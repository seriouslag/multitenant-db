package com.nullspace.multitenant.modules.store.models.entities.system;

import com.nullspace.multitenant.models.entities.BaseEntity;
import com.nullspace.multitenant.modules.store.models.entities.merchant.MerchantStore;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
//@EntityListeners(value = AuditListener.class)
@Table(name = "merchant_configurations", uniqueConstraints=
@UniqueConstraint(columnNames = {"merchant_id", "config_key"}))
public class MerchantConfiguration extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="merchant_id")
    private MerchantStore merchantStore;

    @Column(name="config_key")
    private String key;

    @Column(name="value")
    @Type(type = "org.hibernate.type.TextType")
    private String value;

    @Column(name="type")
    @Enumerated(value = EnumType.STRING)
    private MerchantConfigurationType merchantConfigurationType = MerchantConfigurationType.INTEGRATION;

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public MerchantStore getMerchantStore() {
        return merchantStore;
    }

    public void setMerchantStore(MerchantStore merchantStore) {
        this.merchantStore = merchantStore;
    }

    public void setMerchantConfigurationType(MerchantConfigurationType merchantConfigurationType) {
        this.merchantConfigurationType = merchantConfigurationType;
    }

    public MerchantConfigurationType getMerchantConfigurationType() {
        return merchantConfigurationType;
    }
}
