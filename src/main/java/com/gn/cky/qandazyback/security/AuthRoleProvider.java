package com.gn.cky.qandazyback.security;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthRoleProvider {
    private final AuthRoleRepository repository;

    public AuthRoleProvider(AuthRoleRepository repository) {
        this.repository = repository;
    }

    public List<String> claimRoles(String email) {
        return repository.findRolesByEmail(email);
    }
}
