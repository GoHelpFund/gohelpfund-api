package com.gohelpfund.api.v1.authentication_service.model;

import javax.validation.constraints.NotNull;

public class UserSignUp {
    @NotNull
    private String scope;
    @NotNull
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String password;

    public UserSignUp() {

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
