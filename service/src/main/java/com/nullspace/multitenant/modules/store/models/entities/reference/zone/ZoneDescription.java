package com.nullspace.multitenant.modules.store.models.entities.reference.zone;

import com.nullspace.multitenant.modules.store.models.entities.common.Description;

import javax.persistence.*;

@Entity
@Table(name="zone_descriptions", uniqueConstraints={
        @UniqueConstraint(columnNames={
                "zone_id",
        })
})
public class ZoneDescription extends Description {
    @ManyToOne(targetEntity = Zone.class)
    @JoinColumn(name = "zone_id", nullable = false)
    private Zone zone;

    public ZoneDescription() {
    }

    public ZoneDescription(Zone zone, String name) {
        setZone(zone);
        setName(name);
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }
}
