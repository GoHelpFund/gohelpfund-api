package com.gohelpfund.api.v1.authentication_service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
public class UserRole {
    @Id
    @Column(name = "user_role_id", nullable = false)
    private String id;

    @Column(name = "user_name", nullable = false)
    private String username;

    @Column(name = "role", nullable = false)
    private String role;

    public UserRole() {
    }

    public UserRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserRole withId(String id) {
        this.setId(id);
        return this;
    }

    public UserRole withUsername(String username){
        this.setUsername(username);
        return this;
    }

    public UserRole withRole(String role){
        this.setRole(role);
        return this;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
