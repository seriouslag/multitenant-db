package com.nullspace.multitenant.demo.modules.store.models.entities.user;

import com.nullspace.multitenant.demo.models.entities.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
//@EntityListeners(value = AuditListener.class)
@Table(name = "sm_groups")
public class Group extends BaseEntity {
    public Group() {
    }

    //@NotEmpty
    @Column (name ="group_type")
    @Enumerated(value = EnumType.STRING)
    private GroupType groupType;

    @NotEmpty
    @Column(name="group_name", unique=true)
    private String groupName;

    public Group(String groupName) {
        this.groupName = groupName;
    }

    @ManyToMany(mappedBy = "groups")
    private Set<Permission> permissions = new HashSet<Permission>();

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }

    public GroupType getGroupType() {
        return groupType;
    }
}
