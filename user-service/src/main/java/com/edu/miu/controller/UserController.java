package com.edu.miu.controller;

import com.edu.miu.dto.UserKeycloakDto;
import com.edu.miu.entity.User;
import com.edu.miu.security.AuthHelper;
import com.edu.miu.service.KeycloakService;
import com.edu.miu.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "Business User Services")
public class UserController {

    private final UserService userService;

    private final KeycloakService keycloakService;

    private final AuthHelper authHelper;

    @GetMapping("/{id}")
    public Object getUser(@PathVariable String id) {
        if (id.equals(authHelper.getUserId()) || authHelper.isAdmin()) {
            return keycloakService.getUser(id);
        }
        return null;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String createUser(@RequestBody UserKeycloakDto userKeycloakDto) {
        return keycloakService.createUser(userKeycloakDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN' or 'ROLE_USER')")
    public Object updateUser(@PathVariable String id, @RequestBody UserKeycloakDto userKeycloakDto) {
        if (id.equals(authHelper.getUserId()) || authHelper.isAdmin()) {
            return keycloakService.updateUser(id, userKeycloakDto);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Object deleteUser(@PathVariable String id) {
        return keycloakService.deleteUser(id);
    }
}
