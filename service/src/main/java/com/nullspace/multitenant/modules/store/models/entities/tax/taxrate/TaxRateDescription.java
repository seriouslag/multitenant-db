package com.nullspace.multitenant.modules.store.models.entities.tax.taxrate;

import com.nullspace.multitenant.modules.store.models.entities.common.Description;

import javax.persistence.*;

@Entity
@Table(name = "tax_rate_descriptions", uniqueConstraints={
        @UniqueConstraint(columnNames={
                "tax_rate_id",
        })
}
)
public class TaxRateDescription extends Description {
    @ManyToOne(targetEntity = TaxRate.class)
    @JoinColumn(name = "tax_rate_id")
    private TaxRate taxRate;

    public TaxRateDescription() {
    }

    public TaxRate getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(TaxRate taxRate) {
        this.taxRate = taxRate;
    }
}
