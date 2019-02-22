package com.nullspace.multitenant.modules.store.models.entities.common;

import com.nullspace.multitenant.models.entities.BaseEntity;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;

@MappedSuperclass
//@EntityListeners(value = AuditListener.class)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Description extends BaseEntity {

//    @Embedded
//    private AuditSection auditSection = new AuditSection();

    @NotEmpty
    @Column(name="name", nullable = false, length=120)
    private String name;

    @Column(name="title", length=100)
    private String title;

    @Column(name="description")
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    public Description() {
    }

    public Description(String name) {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
