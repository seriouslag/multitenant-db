package com.nullspace.multitenant.modules.store.models.entities.MarketPlace;

import com.nullspace.multitenant.models.entities.BaseEntity;
import com.nullspace.multitenant.modules.store.models.entities.catalog.Catalog;
import com.nullspace.multitenant.modules.store.models.entities.merchant.MerchantStore;

import java.util.HashSet;
import java.util.Set;

public class MarketPlace extends BaseEntity {
    private MerchantStore store;
    private String code;
    private Set<Catalog> catalogs = new HashSet<>();

//    @Embedded
//    private AuditSection auditSection = new AuditSection();

//    @Override
//    public AuditSection getAuditSection() {
//        return auditSection;
//    }
//
//    @Override
//    public void setAuditSection(AuditSection audit) {
//        this.auditSection = auditSection;
//    }

    public MerchantStore getStore() {
        return store;
    }

    public void setStore(MerchantStore store) {
        this.store = store;
    }

    public Set<Catalog> getCatalogs() {
        return catalogs;
    }

    public void setCatalogs(Set<Catalog> catalogs) {
        this.catalogs = catalogs;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
