package com.example.service;

import com.example.dto.ProductDto;
import com.example.dto.ReviewDto;
import com.example.dto.UserDto;
import com.example.entity.Review;
import com.example.repo.ReviewRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ProductClient productClient;
    @Autowired
    private UserClient userClient;
    @Autowired
    private ReviewRepo reviewRepo;

    private final CircuitBreakerFactory circuitBreakerFactory;
    @Override
    public List<ReviewDto> getAll() {

        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        var products = circuitBreaker.run(() -> productClient.getAllProducts(),
                throwable -> getDefaultProducts());
        CircuitBreaker circuitBreaker1 = circuitBreakerFactory.create("user-breaker");
        var users = circuitBreaker1.run(() -> userClient.getAllUsers(),
                throwable -> getDefaultUsers());
        return ((List<Review>) reviewRepo.findAll()).stream().map( r -> new ReviewDto(r, (List<ProductDto>)products, (List<UserDto>)users)).collect(Collectors.toList());
    }

    public Iterable<ProductDto> getDefaultProducts(){
        return new ArrayList<>();
    }
    public Iterable<UserDto> getDefaultUsers(){
        return new ArrayList<>();
    }

}
