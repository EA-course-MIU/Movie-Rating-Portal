package com.example.dto;

import com.example.entity.Review;
import lombok.Data;

import java.util.List;

@Data
public class ReviewDto {
    private int id;
    private String comment;
    private ProductDto product;
    private UserDto user;

    public ReviewDto(Review review, List<ProductDto> products, List<UserDto> users){
        this.id = review.getId();
        this.comment = review.getComment();
        this.product = products.stream().filter(p -> p.getId() == review.getIdProduct()).findFirst().orElse(null);
        this.user = users.stream().filter(u -> u.getId() == review.getIdUser()).findFirst().orElse(null);
    }
}
