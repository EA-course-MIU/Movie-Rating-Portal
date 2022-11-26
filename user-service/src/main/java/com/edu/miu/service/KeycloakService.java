package com.edu.miu.service;

import com.edu.miu.dto.UserKeycloakDto;

public interface KeycloakService {

    /**
     * Get user
     * @param id
     * @return UserKeycloakDto
     */
    Object getUser(String id);


    /**
     * Create a new Keycloak user
     * @param userKeycloakDto
     * @return user id
     */
    String createUser(UserKeycloakDto userKeycloakDto);

    /**
     * Update a user
     * @param userKeycloakDto
     * @return user id
     */
    Object updateUser(String id, UserKeycloakDto userKeycloakDto);


    /**
     * Delete a Keycloak user
     * @param userId
     */
    Object deleteUser(String userId);

}
