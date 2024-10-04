package com.tin.springcoursera.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {
    @Bean
    Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl("http://localhost:8181")
                .realm("master")
                .clientId("admin-cli")
                .grantType(OAuth2Constants.PASSWORD)
                .username("admin")
                .password("admin")
                .build();
    }
}
