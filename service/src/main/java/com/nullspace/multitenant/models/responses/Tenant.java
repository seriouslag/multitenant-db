package com.nullspace.multitenant.models.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tenant {
    String id;
    String url;

    public Tenant(String id, String url) {
        this.id = id;
        this.url = url;
    }
}
