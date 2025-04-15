package com.gn.cky.qandazyback.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Objects;

public class AuthToken extends AbstractAuthenticationToken {
    private final transient AuthPrincipal authPrincipal;

    public AuthToken(Collection<? extends GrantedAuthority> authorities, AuthPrincipal authPrincipal) {
        super(authorities);
        this.authPrincipal = authPrincipal;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public AuthPrincipal getPrincipal() {
        return authPrincipal;
    }

    @Override
    public final boolean equals(Object o) {
        if(this == o) return true;
        if (!(o instanceof AuthToken authToken)) return false;
        if (!super.equals(o)) return false;

        return Objects.equals(authPrincipal, authToken.authPrincipal);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hashCode(authPrincipal);
        return result;
    }
}
