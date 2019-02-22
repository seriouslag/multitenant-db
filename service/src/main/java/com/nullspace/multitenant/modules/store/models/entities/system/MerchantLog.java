package com.nullspace.multitenant.modules.store.models.entities.system;

import com.nullspace.multitenant.models.entities.BaseEntity;
import com.nullspace.multitenant.modules.store.models.entities.merchant.MerchantStore;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
//@EntityListeners(value = AuditListener.class)
@Table(name = "merchant_logs")
public class MerchantLog extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="merchant_id", nullable=false)
    private MerchantStore store;

    @Column(name="module", length=25)
    private String module;

    @Column(name="log")
    @Type(type = "org.hibernate.type.TextType")
    private String log;

    public MerchantLog(MerchantStore store, String log) {
        this.store = store;
        this.log = log;
    }

    public MerchantLog(MerchantStore store, String module, String log) {
        this.store = store;
        this.module = module;
        this.log = log;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MerchantStore getStore() {
        return store;
    }

    public void setStore(MerchantStore store) {
        this.store = store;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
