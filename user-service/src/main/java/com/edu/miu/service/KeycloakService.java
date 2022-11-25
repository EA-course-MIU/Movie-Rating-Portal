package com.edu.miu.service;

import com.edu.miu.dto.UserKeycloakDto;

public interface KeycloakService {

    /**
     * Create a new Keycloak user
     * @param userKeycloakDto
     * @return user id
     */
    String createUser(UserKeycloakDto userKeycloakDto);

}
