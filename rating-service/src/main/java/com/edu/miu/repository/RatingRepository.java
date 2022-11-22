package com.edu.miu.repository;

import com.edu.miu.entity.Rating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Integer> {

    List<Rating> findAll();

    List<Rating> findByMediaId(int id);


    @Query("SELECT AVG(r.rating) FROM Rating r WHERE r.mediaId = ?1")
    double getAvgRatingToMedia(int id);

}
