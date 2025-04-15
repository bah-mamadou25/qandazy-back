package com.gn.cky.qandazyback.security;

import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;

public class AccessMap {
    private final Map<AntPathRequestMatcher, List<String>> access;

    public AccessMap() {
        access = new HashMap<>();
    }

    public void put(AntPathRequestMatcher matcher, List<String> roles) {
        access.put(matcher, roles);
    }

    public List<String> get(RequestAuthorizationContext rac) {
        for(var entry : access.entrySet()) {
            if (entry.getKey().matches(rac.getRequest())) {
                return entry.getValue();
            }
        }
        return emptyList();
    }
}
