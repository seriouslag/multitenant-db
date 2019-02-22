package com.nullspace.multitenant.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    public boolean isNew() {
        return getId() == null;
    }

    @Override
    public String toString() {
        return "entity." +
                getClass().getSimpleName() +
                "<" +
                getId() +
                "-" +
                super.toString() +
                ">";
    }
}
