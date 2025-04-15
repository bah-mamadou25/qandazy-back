package com.gn.cky.qandazyback.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;

public class AuthJwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    @Override
    public AbstractAuthenticationToken convert(Jwt source) {
        var userName = source.getClaimAsString("preferred_username");
        var email = source.getClaimAsString("email");
        var principal = new AuthPrincipal(userName, email);
        var grantedAuthorities = new ArrayList<GrantedAuthority>();

        for(String role : source.getClaimAsString("roles").split(",")) {
            grantedAuthorities.add(new AuthGrantedAuthority(role.replace("ROLE_", "")));
        }
        var token=new AuthToken(grantedAuthorities, principal);
        token.setAuthenticated(true);
        return token;
    }
}
