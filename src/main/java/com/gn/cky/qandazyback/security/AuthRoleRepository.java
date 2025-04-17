package com.gn.cky.qandazyback.security;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthRoleRepository {
    public List<String> findRolesByEmail(String email) {
        // Query database later
        return List.of("USER");
    }
}
