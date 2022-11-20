package com.example.service;

import com.example.dto.ReviewDto;
import com.example.entity.Review;

import java.util.List;

public interface ReviewService {
    List<ReviewDto> getAll();
}
