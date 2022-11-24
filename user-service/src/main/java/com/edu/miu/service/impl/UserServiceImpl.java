package com.edu.miu.service.impl;

import com.edu.miu.entity.User;
import com.edu.miu.repo.UserRepo;
import com.edu.miu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    private final CircuitBreakerFactory circuitBreakerFactory;
    @Override
    public Iterable<User> getAll() {

        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
//        var products = circuitBreaker.run(() -> productClient.getAllProducts(),
//                throwable -> getDefaultProducts());
        return userRepo.findAll();
    }

}
