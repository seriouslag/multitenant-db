package com.nullspace.multitenant.modules.store.models.entities.catalog;

import com.nullspace.multitenant.models.entities.BaseEntity;
import com.nullspace.multitenant.modules.store.models.entities.merchant.MerchantStore;

import java.util.ArrayList;
import java.util.List;

public class Catalog extends BaseEntity {
    private MerchantStore store;

    private String code;

    private List<CatalogDescription> descriptions = new ArrayList<>();

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<CatalogDescription> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<CatalogDescription> descriptions) {
        this.descriptions = descriptions;
    }

}