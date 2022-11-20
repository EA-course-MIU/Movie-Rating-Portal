package com.example.service;

import com.example.dto.ProductDto;
import com.example.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("USER-SERVICE")
public interface UserClient {
    @GetMapping("/users")
    Iterable<UserDto> getAllUsers();
}
