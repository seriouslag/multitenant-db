package com.nullspace.multitenant.demo.modules.store.models.entities.reference.currency;

import com.nullspace.multitenant.demo.models.entities.BaseEntity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "currencies")
@Cacheable
public class Currency extends BaseEntity {
    @Column(name = "currency_currency_code", nullable = false, unique = true)
    private java.util.Currency currency;

    @Column(name = "currency_supported")
    private Boolean supported = true;

    @Column(name = "currency_code", unique = true)
    private String code;

    @Column(name = "currency_name", unique = true)
    private String name;

    public Currency() {
    }

    public java.util.Currency getCurrency() {
        return currency;
    }

    public void setCurrency(java.util.Currency currency) {
        this.currency = currency;
        this.code = currency.getCurrencyCode();
    }

    public Boolean getSupported() {
        return supported;
    }

    public void setSupported(Boolean supported) {
        this.supported = supported;
    }

    public String getCode() {
        if (currency.getCurrencyCode() != code) {
            return currency.getCurrencyCode();
        }
        return code;
    }

    public String getSymbol() {
        return currency.getSymbol();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
