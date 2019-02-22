package com.nullspace.multitenant.modules.store.models.entities.customer;

import com.nullspace.multitenant.modules.store.models.entities.common.EntityList;

import java.util.List;

public class CustomerList extends EntityList {
    /**
     *
     */
    private static final long serialVersionUID = -3108842276158069739L;
    private List<Customer> customers;
    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
    public List<Customer> getCustomers() {
        return customers;
    }
}
