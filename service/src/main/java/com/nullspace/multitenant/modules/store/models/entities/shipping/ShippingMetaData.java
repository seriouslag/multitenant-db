package com.nullspace.multitenant.modules.store.models.entities.shipping;

import com.nullspace.multitenant.modules.store.models.entities.reference.country.Country;

import java.util.List;

public class ShippingMetaData {
    private List<String> modules;
    private List<String> preProcessors;
    private List<String> postProcessors;
    private List<Country> shipToCountry;
    private boolean useDistanceModule;

    public List<String> getModules() {
        return modules;
    }
    public void setModules(List<String> modules) {
        this.modules = modules;
    }
    public List<String> getPreProcessors() {
        return preProcessors;
    }
    public void setPreProcessors(List<String> preProcessors) {
        this.preProcessors = preProcessors;
    }
    public List<String> getPostProcessors() {
        return postProcessors;
    }
    public void setPostProcessors(List<String> postProcessors) {
        this.postProcessors = postProcessors;
    }
    public List<Country> getShipToCountry() {
        return shipToCountry;
    }
    public void setShipToCountry(List<Country> shipToCountry) {
        this.shipToCountry = shipToCountry;
    }
    public boolean isUseDistanceModule() {
        return useDistanceModule;
    }
    public void setUseDistanceModule(boolean useDistanceModule) {
        this.useDistanceModule = useDistanceModule;
    }
}
