package com.nullspace.multitenant.modules.store.models.entities.common;

public class Address {
    private String city;
    private String postalCode;
    private String stateProvince;
    private String zone;//code
    private String country;//code

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getStateProvince() {
        return stateProvince;
    }

    public String getCountry() {
        return country;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getZone() {
        return zone;
    }
}
