package com.edu.miu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserKeycloakDto {

    private String userName;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    List<String> groups;

}
