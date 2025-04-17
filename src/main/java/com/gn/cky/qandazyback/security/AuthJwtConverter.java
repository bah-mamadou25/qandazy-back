package com.gn.cky.qandazyback.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;


public class AuthJwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private final AuthRoleProvider authRoleProvider;

    public AuthJwtConverter(AuthRoleProvider authRoleProvider) {
        this.authRoleProvider = authRoleProvider;
    }
    @Override
    public AbstractAuthenticationToken convert(Jwt source) {
        var principal = new AuthPrincipal(
                source.getClaimAsString("preferred_username"),
                source.getClaimAsString("email")
        );

        var authorities = authRoleProvider.claimRoles(principal.email()).stream()
                .map(AuthGrantedAuthority::new)
                .toList();

        var token = new AuthToken(authorities, principal);
        token.setAuthenticated(true);
        return token;
    }
}
