package com.nullspace.multitenant.demo.modules.store.models.entities.tax;

import com.nullspace.multitenant.demo.modules.store.models.entities.order.OrderTotalItem;
import com.nullspace.multitenant.demo.modules.store.models.entities.tax.taxrate.TaxRate;

public class TaxItem extends OrderTotalItem {
    private String label;
    private TaxRate taxRate=null;

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setTaxRate(TaxRate taxRate) {
        this.taxRate = taxRate;
    }

    public TaxRate getTaxRate() {
        return taxRate;
    }
}
