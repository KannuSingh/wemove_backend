package com.wemove.wemove_backend.security;

import com.wemove.wemove_backend.entities.UserCredentials;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class SecurityUser implements UserDetails {

    private UserCredentials userCredentials;

    public SecurityUser(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
    }
    @Override
    public String getUsername() {
        return userCredentials.getEmail();
    }
    @Override
    public String getPassword() {
        return userCredentials.getPassword();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(()->"read");
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
