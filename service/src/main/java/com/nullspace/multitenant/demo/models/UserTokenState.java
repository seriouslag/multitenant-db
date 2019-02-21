package com.nullspace.multitenant.demo.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserTokenState {
    private String access_token;
    private Long expires_in;
    private String userId;

    public UserTokenState() {
        this.access_token = null;
        this.expires_in = null;
        this.userId = null;
    }

    public UserTokenState(String access_token, long expires_in, String userId) {
        this.access_token = access_token;
        this.expires_in = expires_in;
        this.userId = userId;
    }
}