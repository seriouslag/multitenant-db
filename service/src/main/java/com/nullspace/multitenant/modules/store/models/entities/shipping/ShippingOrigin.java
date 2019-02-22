package com.nullspace.multitenant.modules.store.models.entities.shipping;

import com.nullspace.multitenant.models.entities.BaseEntity;
import com.nullspace.multitenant.modules.store.models.entities.merchant.MerchantStore;
import com.nullspace.multitenant.modules.store.models.entities.reference.country.Country;
import com.nullspace.multitenant.modules.store.models.entities.reference.zone.Zone;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "shipping_origins")
public class ShippingOrigin extends BaseEntity {
    @Column(name = "active")
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchant_id", nullable = false)
    private MerchantStore merchantStore;

    @NotEmpty
    @Column(name = "street_address", length = 256)
    private String address;

    @NotEmpty
    @Column(name = "city", length = 100)
    private String city;

    @NotEmpty
    @Column(name = "postcode", length = 20)
    private String postalCode;

    @Column(name = "state", length=100)
    private String state;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Country.class)
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Zone.class)
    @JoinColumn(name = "zone_id")
    private Zone zone;

    public MerchantStore getMerchantStore() {
        return merchantStore;
    }

    public void setMerchantStore(MerchantStore merchantStore) {
        this.merchantStore = merchantStore;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }
}
