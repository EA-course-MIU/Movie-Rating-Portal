package com.edu.miu.service.impl;

import com.edu.miu.dto.UserKeycloakDto;
import com.edu.miu.service.KeycloakService;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class KeycloakServiceImpl implements KeycloakService {

    private final String CREDENTIAL_TYPE = "Password";

    @Value("${app.config.keycloak.token-uri}")
    private String authUrl;

    @Value("${app.config.keycloak.url}")
    private String serverUrl;

    @Value("${app.config.keycloak.client-id}")
    private String clientId;

    @Value("${app.config.keycloak.authorization-grant-type}")
    private String grantType;

    @Value("${app.config.keycloak.scope}")
    private String scope;

    @Value("${app.config.keycloak.realm}")
    private String realm;

    @Value("${app.config.keycloak.client-secret}")
    private String secret;

    @Value("${app.config.keycloak.admin.username}")
    private String adminName;

    @Value("${app.config.keycloak.admin.password}")
    private String adminPassword;

    @Override
    public String createUser(UserKeycloakDto userKeycloakDto) {

        Keycloak keycloak = KeycloakBuilder.builder()
            .serverUrl(serverUrl)
            .realm(realm)
            .grantType(OAuth2Constants.PASSWORD)
            .clientId(clientId)
            .clientSecret(secret)
            .username(adminName)
            .password(adminPassword)
            .build();

        RealmResource realmResource = keycloak.realm(realm);
        UsersResource usersRessource = realmResource.users();

        // Create a new keycloak user
        Response response = usersRessource.create(this.createUserRepresentation(userKeycloakDto));

        // Get the user id
        String userId = CreatedResponseUtil.getCreatedId(response);

        System.out.printf("User created with userId: %s%n", userId);

        return userId;
    }

    private UserRepresentation createUserRepresentation(UserKeycloakDto userKeycloakDto) {
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(userKeycloakDto.getUserName());
        user.setFirstName(userKeycloakDto.getFirstName());
        user.setLastName(userKeycloakDto.getLastName());
        user.setEmail(userKeycloakDto.getEmail());
        user.setGroups(userKeycloakDto.getGroups());

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CREDENTIAL_TYPE);
        credential.setValue(userKeycloakDto.getPassword());
        credential.setUserLabel(CREDENTIAL_TYPE);

        List<CredentialRepresentation> credentials = new ArrayList<>();
        credentials.add(credential);
        user.setCredentials(credentials);

        return user;
    }
}
