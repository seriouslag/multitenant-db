package com.nullspace.multitenant.demo.modules.store.models.entities.system;

import com.nullspace.multitenant.demo.models.entities.BaseEntity;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
//@EntityListeners(value = AuditListener.class)
@Table(name = "module_configurations")
public class IntegrationModule extends BaseEntity {
    @Column(name = "module")
    private String module;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "regions")
    private String regions;

    @Column(name = "configuration")
    @Type(type = "org.hibernate.type.TextType")
    private String configuration;

    @Column(name = "details")
    @Type(type = "org.hibernate.type.TextType")
    private String configDetails;

    @Column(name = "type")
    private String type;

    @Column(name = "image")
    private String image;

    @Column(name = "custom_ind")
    private boolean customModule = false;

    @Transient
    private Set<String> regionsSet = new HashSet<>();

    /**
     * Contains a map of module config by environment (DEV,PROD)
     */
    @Transient
    private Map<String, ModuleConfig> moduleConfigs = new HashMap<String, ModuleConfig>();

    @Transient
    private Map<String, String> details = new HashMap<>();

    public Map<String, String> getDetails() {
        return details;
    }

    public void setDetails(Map<String, String> details) {
        this.details = details;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getRegions() {
        return regions;
    }

    public void setRegions(String regions) {
        this.regions = regions;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public void setRegionsSet(Set<String> regionsSet) {
        this.regionsSet = regionsSet;
    }

    public Set<String> getRegionsSet() {
        return regionsSet;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setModuleConfigs(Map<String, ModuleConfig> moduleConfigs) {
        this.moduleConfigs = moduleConfigs;
    }

    public Map<String, ModuleConfig> getModuleConfigs() {
        return moduleConfigs;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setCustomModule(boolean customModule) {
        this.customModule = customModule;
    }

    public boolean isCustomModule() {
        return customModule;
    }

    public String getConfigDetails() {
        return configDetails;
    }

    public void setConfigDetails(String configDetails) {
        this.configDetails = configDetails;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
