package com.edu.miu.controller;

import com.edu.miu.entity.User;
import com.edu.miu.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "Business User Services")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public Iterable<User> getAll(){
        return userService.getAll();
    }
}
