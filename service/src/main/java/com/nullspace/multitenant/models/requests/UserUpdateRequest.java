package com.nullspace.multitenant.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequest {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    @JsonProperty("isEnabled")
    private boolean isEnabled;

    public UserUpdateRequest() {
        isEnabled = true;
    }

    public UserUpdateRequest(Long id, String email, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = email;
        this.isEnabled = true;
    }

    public UserUpdateRequest(Long id, String email, String firstName, String lastName, @JsonProperty("isEnabled") boolean isEnabled) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = email;
        this.isEnabled = isEnabled;
    }

    public UserUpdateRequest(Long id, String email, String password, String firstName, String lastName, String username) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.isEnabled = true;
    }

    public UserUpdateRequest(Long id, String email, String password, String firstName, String lastName, String username, @JsonProperty("isEnabled") boolean isEnabled) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.isEnabled = isEnabled;
    }
}
