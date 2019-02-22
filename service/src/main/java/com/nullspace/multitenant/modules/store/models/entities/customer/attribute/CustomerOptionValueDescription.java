package com.nullspace.multitenant.modules.store.models.entities.customer.attribute;

import com.nullspace.multitenant.modules.store.models.entities.common.Description;

import javax.persistence.*;

@Entity
@Table(name = "customer_opt_val_descriptions", uniqueConstraints={
        @UniqueConstraint(columnNames={
                "customer_opt_val_id",
        })
})
public class CustomerOptionValueDescription extends Description {
    @ManyToOne(targetEntity = CustomerOptionValue.class)
    @JoinColumn(name = "customer_opt_val_id")
    private CustomerOptionValue customerOptionValue;

    public CustomerOptionValueDescription() {
    }

    public CustomerOptionValue getCustomerOptionValue() {
        return customerOptionValue;
    }

    public void setCustomerOptionValue(CustomerOptionValue customerOptionValue) {
        this.customerOptionValue = customerOptionValue;
    }
}
