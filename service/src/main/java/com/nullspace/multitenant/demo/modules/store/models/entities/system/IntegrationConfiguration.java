package com.nullspace.multitenant.demo.modules.store.models.entities.system;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IntegrationConfiguration implements JSONAware {
    public final static String TEST_ENVIRONMENT = "TEST";
    public final static String PRODUCTION_ENVIRONMENT = "PRODUCTION";

    private String moduleCode;
    private boolean active;
    private boolean defaultSelected;
    //private boolean customModule;
    private Map<String,String> integrationKeys= new HashMap<>();
    private Map<String, List<String>> integrationOptions= new HashMap<>();
    private String environment;

    public String getModuleCode() {
        return moduleCode;
    }

    @JsonProperty("moduleCode")
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }
    public boolean isActive() {
        return active;
    }

    @JsonProperty("active")
    public void setActive(boolean active) {
        this.active = active;
    }
    public Map<String, String> getIntegrationKeys() {
        return integrationKeys;
    }

    @JsonProperty("integrationKeys")
    public void setIntegrationKeys(Map<String, String> integrationKeys) {
        this.integrationKeys = integrationKeys;
    }

    protected String getJsonInfo() {
        //returnString.append("\"customModule\"").append(":").append(this.isCustomModule());
        //returnString.append(",");
        //returnString.append("}");
        String returnString = "{" +
                "\"moduleCode\"" + ":\"" + this.getModuleCode() + "\"" +
                "," +
                "\"active\"" + ":" + this.isActive() +
                "," +
                "\"defaultSelected\"" + ":" + this.isDefaultSelected() +
                "," +
                "\"environment\"" + ":\"" + this.getEnvironment() + "\"";
        return returnString;
    }

    @SuppressWarnings("unchecked")
    @Override
    public String toJSONString() {
        StringBuilder returnString = new StringBuilder();
        returnString.append(getJsonInfo());

        if(this.getIntegrationKeys().size()>0) {

            JSONObject data = new JSONObject();
            Set<String> keys = this.getIntegrationKeys().keySet();
            for(String key : keys) {
                data.put(key, this.getIntegrationKeys().get(key));
            }
            String dataField = data.toJSONString();

            returnString.append(",").append("\"integrationKeys\"").append(":");
            returnString.append(dataField);
        }

        if(this.getIntegrationOptions()!=null && this.getIntegrationOptions().size()>0) {
            //JSONObject data = new JSONObject();
            StringBuilder optionDataEntries = new StringBuilder();
            Set<String> keys = this.getIntegrationOptions().keySet();
            int countOptions = 0;
            int keySize = 0;

            for(String key : keys) {
                List<String> values = this.getIntegrationOptions().get(key);
                if(values!=null) {
                    keySize ++;
                }
            }

            for(String key : keys) {

                List<String> values = this.getIntegrationOptions().get(key);
                if(values==null) {
                    continue;
                }
                StringBuilder optionsEntries = new StringBuilder();
                StringBuilder dataEntries = new StringBuilder();

                int count = 0;
                for(String value : values) {

                    dataEntries.append("\"").append(value).append("\"");
                    if(count<values.size()-1) {
                        dataEntries.append(",");
                    }
                    count++;
                }

                optionsEntries.append("[").append(dataEntries.toString()).append("]");

                optionDataEntries.append("\"").append(key).append("\":").append(optionsEntries.toString());

                if(countOptions<keySize-1) {
                    optionDataEntries.append(",");
                }
                countOptions ++;
            }
            String dataField = optionDataEntries.toString();

            returnString.append(",").append("\"integrationOptions\"").append(":{");
            returnString.append(dataField);
            returnString.append("}");
        }

        returnString.append("}");
        return returnString.toString();
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getEnvironment() {
        return environment;
    }

    public Map<String,List<String>> getIntegrationOptions() {
        return integrationOptions;
    }

    public void setIntegrationOptions(Map<String,List<String>> integrationOptions) {
        this.integrationOptions = integrationOptions;
    }

    public boolean isDefaultSelected() {
        return defaultSelected;
    }

    public void setDefaultSelected(boolean defaultSelected) {
        this.defaultSelected = defaultSelected;
    }
}
