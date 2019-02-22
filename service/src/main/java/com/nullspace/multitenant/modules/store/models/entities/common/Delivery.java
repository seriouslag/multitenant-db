package com.nullspace.multitenant.modules.store.models.entities.common;

import com.nullspace.multitenant.modules.store.models.entities.reference.country.Country;
import com.nullspace.multitenant.modules.store.models.entities.reference.zone.Zone;

import javax.persistence.*;

@Embeddable
public class Delivery {
    @Column (name ="delivery_last_name", length=64)
    private String lastName;

    @Column (name ="delivery_first_name", length=64)
    private String firstName;

    @Column (name ="delivery_company", length=100)
    private String company;

    @Column (name ="delivery_street_address", length=256)
    private String address;

    @Column (name ="delivery_city", length=100)
    private String city;

    @Column (name ="delivery_postcode", length=20)
    private String postalCode;

    @Column (name ="delivery_state", length=100)
    private String state;

    @Column(name="delivery_telephone", length=32)
    private String telephone;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Country.class)
    @JoinColumn(name = "delivery_country_id")
    private Country country;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Zone.class)
    @JoinColumn(name = "delivery_zone_id")
    private Zone zone;

    @Transient
    private String latitude = null;

    @Transient
    private String longitude = null;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
