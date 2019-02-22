package com.nullspace.multitenant.modules.store.models.entities.user;

import com.nullspace.multitenant.models.entities.BaseEntity;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
//@EntityListeners(value = AuditListener.class)
@Table(name = "permissions")
public class Permission extends BaseEntity {
    public Permission() {
    }

    public Permission(String permissionName) {
        this.permissionName = permissionName;
    }

    @NotEmpty
    @Column(name="permission_name", unique=true)
    private String permissionName;

    @ManyToMany(fetch=FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinTable(name = "permission_groups", joinColumns = {
            @JoinColumn(name = "permission_id", nullable = false, updatable = false) }
            ,
            inverseJoinColumns = { @JoinColumn(name = "group_id",
                    nullable = false, updatable = false) }
    )
    @Cascade({
            org.hibernate.annotations.CascadeType.DETACH,
            org.hibernate.annotations.CascadeType.LOCK,
            org.hibernate.annotations.CascadeType.REFRESH,
            org.hibernate.annotations.CascadeType.REPLICATE

    })
    private List<Group> groups = new ArrayList<>();

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<Group> getGroups() {
        return groups;
    }
}
