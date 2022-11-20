package com.example.repo;

import com.example.entity.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewRepo extends CrudRepository<Review, Integer> {
}
