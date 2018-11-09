package com.gohelpfund.api.v1.authentication.model;

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

    private boolean accountNonExpired, accountNonLocked, credentialsNonExpired, enabled;


    public PlatformUserDetails(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = translate(user.getRoles());
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
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
}