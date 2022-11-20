package com.example.controller;

import com.example.dto.ReviewDto;
import com.example.entity.Review;
import com.example.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @GetMapping
    public List<ReviewDto> getAll(){
        return reviewService.getAll();
    }
}
