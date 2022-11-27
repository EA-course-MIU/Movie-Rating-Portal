package com.edu.miu.service.impl;

import com.edu.miu.config.vault.KeycloakConfiguration;
import com.edu.miu.model.LoginRequest;
import com.edu.miu.model.LoginResponse;
import com.edu.miu.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RequiredArgsConstructor
@Service
public class LoginServiceImpl implements LoginService {

    @Value("${app.config.keycloak.token-uri}")
    private String authUrl;

    @Value("${app.config.keycloak.authorization-grant-type}")
    private String grantType;

    @Value("${app.config.keycloak.scope}")
    private String scope;

    private final RestTemplate restTemplate;

    private final KeycloakConfiguration keycloak;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        HttpHeaders headers = createHeaders(keycloak.getClientId(), keycloak.getClientSecret());

        MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("client_secret", keycloak.getClientSecret());
        bodyMap.add("client_id", keycloak.getClientId());
        bodyMap.add("grant_type", grantType);
        bodyMap.add("scope", scope);
        bodyMap.add("username", loginRequest.getUserName());
        bodyMap.add("password", loginRequest.getPassword());

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(bodyMap, headers);
        return restTemplate.postForEntity(authUrl, requestEntity, LoginResponse.class).getBody();
    }

    private HttpHeaders createHeaders(String username, String password){
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.getEncoder().encode(
                    auth.getBytes(StandardCharsets.US_ASCII) );
            String authHeader = "Basic " + new String( encodedAuth );
//            set( "Authorization", authHeader );
            set("Content-Type", "application/x-www-form-urlencoded");
        }};
    }
}
