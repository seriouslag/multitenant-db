package com.nullspace.multitenant.modules.store.models.entities.system;

import com.nullspace.multitenant.models.entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
//@EntityListeners(value = AuditListener.class)
@Table(name = "system_configurations")
public class SystemConfiguration extends BaseEntity {
    @Column(name="config_key")
    private String key;

    @Column(name="value")
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
