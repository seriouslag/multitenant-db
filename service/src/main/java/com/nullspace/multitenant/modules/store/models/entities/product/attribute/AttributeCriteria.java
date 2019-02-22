package com.nullspace.multitenant.modules.store.models.entities.product.attribute;

public class AttributeCriteria {
    private String attributeCode;
    private String attributeValue;
    public void setAttributeCode(String attributeCode) {
        this.attributeCode = attributeCode;
    }
    public String getAttributeCode() {
        return attributeCode;
    }
    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }
    public String getAttributeValue() {
        return attributeValue;
    }
}
