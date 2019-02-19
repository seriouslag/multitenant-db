package com.nullspace.multitenant.demo.modules.store.models.entities.reference.geozone;

import com.nullspace.multitenant.demo.modules.store.models.entities.common.Description;

import javax.persistence.*;

@Entity
@Table(name="geozone_descriptions", uniqueConstraints={
        @UniqueConstraint(columnNames={
                "geozone_id",
        })
}
)
public class GeoZoneDescription extends Description {
    @ManyToOne(targetEntity = GeoZone.class)
    @JoinColumn(name = "geozone_id")
    private GeoZone geoZone;

    public GeoZoneDescription() {
    }

    public GeoZone getGeoZone() {
        return geoZone;
    }

    public void setGeoZone(GeoZone geoZone) {
        this.geoZone = geoZone;
    }
}
