package com.nullspace.multitenant.demo.modules.store.models.entities.reference.zone;

import com.nullspace.multitenant.demo.models.entities.BaseEntity;
import com.nullspace.multitenant.demo.modules.store.models.entities.reference.country.Country;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "zones")
public class Zone extends BaseEntity {
    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL)
    private List<ZoneDescription> descriptions = new ArrayList<ZoneDescription>();

    @ManyToOne
    @JoinColumn(name="country_id", nullable = false)
    private Country country;

    @Transient
    private String name;

    @Column(name = "zone_code", unique=true, nullable = false)
    private String code;

    public Zone() {
    }

    public Zone(Country country, String name, String code) {
        this.setCode(code);
        this.setCountry(country);
        this.setCode(name);
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
    public List<ZoneDescription> getDescriptions() {
        return descriptions;
    }

    public void setDescriptons(List<ZoneDescription> descriptions) {
        this.descriptions = descriptions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
