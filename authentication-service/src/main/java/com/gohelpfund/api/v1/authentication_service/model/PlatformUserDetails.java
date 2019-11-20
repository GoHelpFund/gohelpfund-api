package com.gohelpfund.api.v1.authentication_service.model;

import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PlatformUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;
    private Collection<? extends GrantedAuthority> authorities;
    private String password;
    private String username;

    @JsonProperty("fundraiser_id")
    private String fundraiserId;

    @JsonProperty("password_changed")
    private boolean passwordChanged;

    @JsonProperty("account_non_expired")
    private boolean accountNonExpired;

    @JsonProperty("account_non_locked")
    private boolean accountNonLocked;

    @JsonProperty("credentials_non_expired")
    private boolean credentialsNonExpired;

    private boolean enabled;

    public PlatformUserDetails(User user) {
        this.username = user.getUsername();
        this.fundraiserId = user.getFundraiserId();
        this.password = user.getPassword();
        this.authorities = translate(user.getRoles());
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = user.isEnabled();
        this.passwordChanged = user.isPasswordChanged();
    }


    private Collection<? extends GrantedAuthority> translate(List<UserRole> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRole role : roles) {
            String name = role.getRole().toUpperCase();
            if (!name.startsWith("ROLE_")) {
                name = "ROLE_" + name;
            }
            authorities.add(new SimpleGrantedAuthority(name));
        }
        return authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getFundraiserId() {
        return fundraiserId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public boolean isPasswordChanged() {
        return passwordChanged;
    }

    public void setPasswordChanged(boolean passwordChanged) {
        this.passwordChanged = passwordChanged;
    }

    @Override
    public String toString() {
        return "PlatformUserDetails{" +
                "authorities=" + authorities +
                ", password='" + "[PROTECTED]" + '\'' +
                ", username='" + username + '\'' +
                ", fundraiserId='" + fundraiserId + '\'' +
                ", passwordChanged=" + passwordChanged +
                ", accountNonExpired=" + accountNonExpired +
                ", accountNonLocked=" + accountNonLocked +
                ", credentialsNonExpired=" + credentialsNonExpired +
                ", enabled=" + enabled +
                '}';
    }
}