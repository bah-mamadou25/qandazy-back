package com.gn.cky.qandazyback.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

import java.util.function.Supplier;

public class AuthManager implements AuthorizationManager<RequestAuthorizationContext> {
    private static final Logger logger = LoggerFactory.getLogger(AuthManager.class);
    private final AccessMap accessMap;

    public AuthManager(AccessMap accessMap) {
        this.accessMap = accessMap;
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext rac) {
        final var authorized= new AuthorizationDecision(true);
        final var nonAuthorized= new AuthorizationDecision(false);
        try{
            var requiredRoles = accessMap.get(rac);
            if(requiredRoles ==null || requiredRoles.isEmpty()) {
                return nonAuthorized;
            }
            logger.info("requiredRoles: {}", requiredRoles);
            var hasRequiredRole = authentication.get().getAuthorities().stream()
                    .anyMatch(grantedAuthority -> requiredRoles.contains(grantedAuthority.getAuthority()));
            return hasRequiredRole ? authorized : nonAuthorized;
        } catch (Exception e){
            logger.error("Error checking authorization", e);
            return nonAuthorized;
        }
    }
}
