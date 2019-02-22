package com.nullspace.multitenant.modules.store.models.entities.reference.country;

import com.nullspace.multitenant.models.entities.BaseEntity;
import com.nullspace.multitenant.modules.store.models.entities.reference.geozone.GeoZone;
import com.nullspace.multitenant.modules.store.models.entities.reference.zone.Zone;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "countries")
@Cacheable
public class Country extends BaseEntity {
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private Set<CountryDescription> descriptions = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "country")
    private Set<Zone> zones = new HashSet<>();

    @ManyToOne(targetEntity = GeoZone.class)
    @JoinColumn(name = "geozone_id")
    private GeoZone geoZone;

    @Column(name = "country_supported")
    private boolean supported = true;

    @Column(name = "country_isocode", unique = true, nullable = false)
    private String isoCode;

    @Transient
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country() {
    }

    public Country(String isoCode) {
        this.setIsoCode(isoCode);
    }

    public boolean getSupported() {
        return supported;
    }

    public void setSupported(boolean supported) {
        this.supported = supported;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public Set<Zone> getZones() {
        return zones;
    }

    public void setZones(Set<Zone> zones) {
        this.zones = zones;
    }

    public GeoZone getGeoZone() {
        return geoZone;
    }

    public void setGeoZone(GeoZone geoZone) {
        this.geoZone = geoZone;
    }


    public Set<CountryDescription> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(Set<CountryDescription> descriptions) {
        this.descriptions = descriptions;
    }
}
