package com.gohelpfund.api.v1.authentication_service.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User{

    @Column(name = "user_id", nullable = false, updatable = false)
    private String id;

    @Column(name = "fundraiser_id", nullable = false)
    private String fundraiserId;

    @Column(name = "fundraiser_type", nullable = false)
    private String fundraiserType;

    @Id
    @Column(name = "user_name", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "password_changed", columnDefinition = "boolean default false", nullable = false)
    private boolean passwordChanged;

    @Column(name = "enabled", columnDefinition = "boolean default true", nullable = false)
    private boolean enabled;


    @JoinColumn(name = "user_name")
    @OneToMany(fetch = FetchType.EAGER)
    @ElementCollection(targetClass = UserRole.class)
    private List<UserRole> roles;

    public User(){
    }

    public User(UserSignUp signUp){
        this.username = signUp.getUsername();
        this.email = signUp.getEmail();
        this.password = signUp.getPassword();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFundraiserId() {
        return fundraiserId;
    }

    public void setFundraiserId(String fundraiserId) {
        this.fundraiserId = fundraiserId;
    }

    public String getFundraiserType() {
        return fundraiserType;
    }

    public void setFundraiserType(String fundraiserType) {
        this.fundraiserType = fundraiserType;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public boolean isPasswordChanged() {
        return passwordChanged;
    }

    public void setPasswordChanged(boolean passwordChanged) {
        this.passwordChanged = passwordChanged;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public User withId(String id){
        this.setId(id);
        return this;
    }

    public User withUsername(String username){
        this.setUsername(username);
        return this;
    }

    public User withFundraiserId(String fundraiserId) {
        this.setFundraiserId(fundraiserId);
        return this;
    }

    public User withFundraiserType(String fundraiserType) {
        this.setFundraiserType(fundraiserType);
        return this;
    }

    public User withEmail(String email){
        this.setEmail(email);
        return this;
    }

    public User withPassword(String password){
        this.setPassword(password);
        return this;
    }

    public User withRoles(List<UserRole> roles) {
        if (roles != null) {
            this.setRoles(roles);
        }
        return this;
    }

    public User withPasswordChanged(boolean changed){
        this.setPasswordChanged(changed);
        return this;
    }

    public User withEnabled(boolean enabled){
        this.setEnabled(enabled);
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password=[PROTECTED];"+
                ", enabled=" + enabled +
                ", passwordChanged=" + passwordChanged +
                ", roles=" + roles +
                '}';
    }
}