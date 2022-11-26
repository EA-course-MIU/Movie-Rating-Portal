package com.edu.miu.service;

import com.edu.miu.model.LoginRequest;
import com.edu.miu.model.LoginResponse;

public interface LoginService {

    /**
     * Call login to the keycloak to get token
     * @param loginRequest
     * @return LoginResponse
     */
    LoginResponse login(LoginRequest loginRequest);

}
