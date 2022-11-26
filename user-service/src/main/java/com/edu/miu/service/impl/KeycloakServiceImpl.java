package com.edu.miu.service.impl;

import com.edu.miu.dto.UserKeycloakDto;
import com.edu.miu.publisher.PublisherService;
import com.edu.miu.service.KeycloakService;
import lombok.RequiredArgsConstructor;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
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

    private final ModelMapper modelMapper;

    private final PublisherService publisherService;

    @Override
    public Object getUser(String id) {
        UsersResource usersResource = this.getUsersResource();
//        return modelMapper.map(usersResource.get(id), UserKeycloakDto.class);
        return this.getUserRepresentation(id, usersResource);
    }

    @Override
    public String createUser(UserKeycloakDto userKeycloakDto) {
        UsersResource usersResource = this.getUsersResource();
        // Create a new keycloak user
        Response response = usersResource.create(this.createUserRepresentation(userKeycloakDto));

        // Get the user id
        String userId = CreatedResponseUtil.getCreatedId(response);

        System.out.printf("User created with userId: %s%n", userId);

        return userId;
    }

    @Override
    public Object updateUser(String id, UserKeycloakDto updated) {
        try {
            UserResource userResource = this.getUsersResource().get(id);
            UserRepresentation current = userResource.toRepresentation();

            if (updated.getEmail() != null && !current.getEmail().equals(updated.getEmail())) {
                current.setEmail(updated.getEmail());
            }

            if (updated.getFirstName() != null && !current.getFirstName().equals(updated.getFirstName())) {
                current.setFirstName(updated.getFirstName());
            }

            if (updated.getLastName() != null && !current.getLastName().equals(updated.getLastName())) {
                current.setLastName(updated.getLastName());
            }

            if (updated.getGroups() != null) {
                current.setGroups(updated.getGroups());
            }

            if (updated.getPassword() != null) {
                boolean isExisted = false;
                for (CredentialRepresentation credential : current.getCredentials()) {
                    if (credential.getType().equals(CREDENTIAL_TYPE)) {
                        if (!credential.getValue().equals(updated.getPassword())) {
                            credential.setValue(updated.getPassword());
                        }
                        isExisted = true;
                    }
                }
                if (!isExisted) {
                    CredentialRepresentation credential = new CredentialRepresentation();
                    credential.setType(CREDENTIAL_TYPE);
                    credential.setValue(updated.getPassword());
                    credential.setUserLabel(CREDENTIAL_TYPE);
                    current.getCredentials().add(credential);
                }
            }

            userResource.update(current);
            return current;
        } catch (Exception e) {
            System.out.println("User is not exist" + id);
            return null;
        }
    }

    @Override
    public Object deleteUser(String userId) {
        UsersResource usersResource = this.getUsersResource();
        UserRepresentation userRepresentation = this.getUserRepresentation(userId, usersResource);
        if (userRepresentation != null) {
            usersResource.delete(userId);
            publisherService.sendDeleteUserMessage(userId);
        }
        return userRepresentation;
    }

    private UserRepresentation getUserRepresentation(String id, UsersResource usersResource) {
        try {
            return usersResource.get(id).toRepresentation();
        } catch (Exception e) {
            System.out.println("User is not exist" + id);
            return null;
        }
    }

    private UsersResource getUsersResource() {
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
        return realmResource.users();
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
