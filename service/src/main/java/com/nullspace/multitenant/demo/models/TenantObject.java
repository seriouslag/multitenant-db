package com.nullspace.multitenant.demo.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TenantObject {
    String id;
    String url;

    public TenantObject(String id, String url) {
        this.id = id;
        this.url = url;
    }
}
