package com.nullspace.multitenant.demo.modules.store.models.entities.product;

import com.nullspace.multitenant.demo.modules.store.models.entities.common.EntityList;

import java.util.ArrayList;
import java.util.List;

public class ProductList extends EntityList {
    private List<Product> products = new ArrayList<Product>();
    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
