package com.nullspace.multitenant.modules.store.models.entities.reference.country;

import com.nullspace.multitenant.modules.store.models.entities.common.Description;

import javax.persistence.*;

@Entity
@Table(name = "country_descriptions", uniqueConstraints={
        @UniqueConstraint(columnNames={
                "country_id",
        })
})
public class CountryDescription extends Description {
    @ManyToOne(targetEntity = Country.class)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    public CountryDescription() {
    }

    public CountryDescription(String name) {
        this.setName(name);
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
