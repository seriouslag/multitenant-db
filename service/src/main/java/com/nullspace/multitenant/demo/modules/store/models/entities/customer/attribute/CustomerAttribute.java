package com.nullspace.multitenant.demo.modules.store.models.entities.customer.attribute;

import com.nullspace.multitenant.demo.models.entities.BaseEntity;
import com.nullspace.multitenant.demo.modules.store.models.entities.customer.Customer;

import javax.persistence.*;

@Entity
@Table(name="customer_attributes",
        uniqueConstraints={
                @UniqueConstraint(columnNames={
                        "option_id",
                        "customer_id"
                })
        }
)
public class CustomerAttribute extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="option_id", nullable=false)
    private CustomerOption customerOption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="option_value_id", nullable=false)
    private CustomerOptionValue customerOptionValue;

    @Column(name="customer_attr_txt_val")
    private String textValue;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public CustomerAttribute() {
    }

    public CustomerOption getCustomerOption() {
        return customerOption;
    }

    public void setCustomerOption(CustomerOption customerOption) {
        this.customerOption = customerOption;
    }

    public CustomerOptionValue getCustomerOptionValue() {
        return customerOptionValue;
    }

    public void setCustomerOptionValue(CustomerOptionValue customerOptionValue) {
        this.customerOptionValue = customerOptionValue;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    public String getTextValue() {
        return textValue;
    }
}
