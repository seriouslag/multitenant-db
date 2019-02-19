package com.nullspace.multitenant.demo.modules.store.models.entities.reference.geozone;

import com.nullspace.multitenant.demo.models.entities.BaseEntity;
import com.nullspace.multitenant.demo.modules.store.models.entities.reference.country.Country;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "geozones")
public class GeoZone extends BaseEntity {
    @OneToMany(mappedBy = "geoZone", cascade = CascadeType.ALL)
    private List<GeoZoneDescription> descriptions = new ArrayList<GeoZoneDescription>();

    @OneToMany(mappedBy = "geoZone", targetEntity = Country.class)
    private List<Country> countries = new ArrayList<Country>();



    @Column(name = "geozone_name")
    private String name;

    @Column(name = "geozone_code")
    private String code;

    public GeoZone() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public List<GeoZoneDescription> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<GeoZoneDescription> descriptions) {
        this.descriptions = descriptions;
    }
}
