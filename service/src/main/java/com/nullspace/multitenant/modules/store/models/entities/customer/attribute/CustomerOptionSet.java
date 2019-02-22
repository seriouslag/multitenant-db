package com.nullspace.multitenant.modules.store.models.entities.customer.attribute;

import com.nullspace.multitenant.models.entities.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name="customer_option_sets",
        uniqueConstraints={
                @UniqueConstraint(columnNames={
                        "customer_option_id",
                        "customer_option_value_id"
                })
        }
)
    public class CustomerOptionSet extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_option_id", nullable=false)
    private CustomerOption customerOption = null;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_option_value_id", nullable=false)
    private CustomerOptionValue customerOptionValue = null;

    @Column(name="sort_order")
    private Integer sortOrder = 0;

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public void setCustomerOptionValue(CustomerOptionValue customerOptionValue) {
        this.customerOptionValue = customerOptionValue;
    }

    public CustomerOptionValue getCustomerOptionValue() {
        return customerOptionValue;
    }

    public void setCustomerOption(CustomerOption customerOption) {
        this.customerOption = customerOption;
    }

    public CustomerOption getCustomerOption() {
        return customerOption;
    }
}
