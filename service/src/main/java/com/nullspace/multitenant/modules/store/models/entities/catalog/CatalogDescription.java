package com.nullspace.multitenant.modules.store.models.entities.catalog;

import com.nullspace.multitenant.modules.store.models.entities.common.Description;

public class CatalogDescription extends Description {
    private Catalog catalog;

    public CatalogDescription() {
    }

    public CatalogDescription(String name) {
        this.setName(name);
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }
}