package com.nullspace.multitenant.demo.models.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TenantRequest {
    private String url;
    private String username;
    private String password;
    private String rootUserFirstName;
    private String rootUserLastName;
    private String rootUserEmail;
    private String rootUserPassword;


    public TenantRequest(String url, String username, String password, String rootUserFirstName, String rootUserLastName, String rootUserEmail, String rootUserPassword) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.rootUserFirstName = rootUserFirstName;
        this.rootUserLastName = rootUserLastName;
        this.rootUserEmail = rootUserEmail;
        this.rootUserPassword = rootUserPassword;
        // TODO change to accept list of root users
    }
}
