package com.gn.cky.qandazyback.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public AuthJwtConverter authJwtConverter(AuthRoleProvider authRoleProvider) {
        return new AuthJwtConverter(authRoleProvider);
    }

    @Bean
    public AuthManager authManager() {
        var accessMap = new AccessMap();
        accessMap.put(AntPathRequestMatcher.antMatcher("/poc"), List.of("USER"));
        return new AuthManager(accessMap);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthRoleProvider roleProvider) throws Exception {
        return httpSecurity
                .cors(Customizer.withDefaults())
                .csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().access(authManager()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(oauth2rs -> oauth2rs
                        .jwt(configurer -> configurer.jwtAuthenticationConverter(authJwtConverter(roleProvider)))).build();
    }
}
