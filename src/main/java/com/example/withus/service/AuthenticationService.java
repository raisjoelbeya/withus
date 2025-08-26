package com.example.withus.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class AuthenticationService {

    @Value("${keycloak.auth-server-url}")
    private String keycloakServerUrl;

    @Value("${keycloak.realm}")
    private String realm;

    private final WebClient webClient;

    public AuthenticationService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Object login(String username, String password) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "password");
        formData.add("client_id", "withus-client");
        formData.add("username", username);
        formData.add("password", password);

        return webClient.post()
                .uri(keycloakServerUrl + "/realms/" + realm + "/protocol/openid-connect/token")
                .bodyValue(formData)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }
}
