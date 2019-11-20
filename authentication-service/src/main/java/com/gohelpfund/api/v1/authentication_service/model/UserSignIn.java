package com.gohelpfund.api.v1.authentication_service.model;

import javax.validation.constraints.NotNull;

public class UserSignIn {
    @NotNull
    private String grant_type;

    @NotNull
    private String scope;

    private String username;

    private String password;

    public UserSignIn() {

    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
