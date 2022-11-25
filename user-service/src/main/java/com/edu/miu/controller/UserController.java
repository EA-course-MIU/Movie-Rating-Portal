package com.edu.miu.controller;

import com.edu.miu.dto.UserKeycloakDto;
import com.edu.miu.entity.User;
import com.edu.miu.service.KeycloakService;
import com.edu.miu.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "Business User Services")
public class UserController {

    private final UserService userService;

    private final KeycloakService keycloakService;
    @GetMapping
    public Iterable<User> getAll(){
        return userService.getAll();
    }

    @PostMapping
    public String createUser(@RequestBody UserKeycloakDto userKeycloakDto) {
        return keycloakService.createUser(userKeycloakDto);
    }
}
