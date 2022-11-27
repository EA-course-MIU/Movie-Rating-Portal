package com.edu.miu.config.vault;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("keycloak")
@Data
public class KeycloakConfiguration {

    private String username;

    private String password;

    private String publicKey;

}
