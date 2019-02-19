package com.nullspace.multitenant.demo.modules.store.models.entities.common;

import com.nullspace.multitenant.demo.modules.store.models.entities.reference.country.Country;
import com.nullspace.multitenant.demo.modules.store.models.entities.reference.zone.Zone;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Embeddable
public class Billing {
    @NotEmpty
    @Column(name ="billing_last_name", length=64, nullable=false)
    private String lastName;

    @NotEmpty
    @Column (name ="billing_first_name", length=64, nullable=false)
    private String firstName;

    @Column (name ="billing_company", length=100)
    private String company;

    @Column (name ="billing_street_address", length=256)
    private String address;

    @Column (name ="billing_city", length=100)
    private String city;

    @Column (name ="billing_postcode", length=20)
    private String postalCode;

    @Column(name="billing_telephone", length=32)
    private String telephone;

    @Column (name ="billing_state", length=100)
    private String state;

    @Column (name ="longitude", length=100)
    private String longitude;

    @Column (name ="latitude", length=100)
    private String latitude;


    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Country.class)
    @JoinColumn(name="billing_country_id", nullable=false)
    private Country country;


    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Zone.class)
    @JoinColumn(name="billing_zone_id", nullable=true)
    private Zone zone;

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

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

}
