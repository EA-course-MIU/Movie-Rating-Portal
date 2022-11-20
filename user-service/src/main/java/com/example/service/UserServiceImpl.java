package com.example.service;

import com.example.dto.ProductDto;
import com.example.entity.User;
import com.example.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    @Autowired
    private ProductClient productClient;
    @Autowired
    private UserRepo userRepo;

    private final CircuitBreakerFactory circuitBreakerFactory;
    @Override
    public Iterable<User> getAll() {

        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        var products = circuitBreaker.run(() -> productClient.getAllProducts(),
                throwable -> getDefaultProducts());
        return userRepo.findAll();
    }

    public Iterable<ProductDto> getDefaultProducts(){
        return new ArrayList<>();
    }

}
