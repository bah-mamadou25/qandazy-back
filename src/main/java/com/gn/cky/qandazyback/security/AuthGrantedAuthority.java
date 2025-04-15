package com.gn.cky.qandazyback.security;

import org.springframework.security.core.GrantedAuthority;

public class AuthGrantedAuthority implements GrantedAuthority {
    private final String role;

    public AuthGrantedAuthority(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
