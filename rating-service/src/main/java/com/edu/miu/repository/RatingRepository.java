package com.edu.miu.repository;

import com.edu.miu.entity.Rating;
import com.edu.miu.enums.MediaType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Integer> {

    List<Rating> findAll();

    List<Rating> findByMediaId(int id);


    Rating findByUserIdAndMediaIdAndMediaType(String userId, int mediaId, MediaType mediaType);


    @Query("SELECT AVG(r.rating) FROM Rating r WHERE r.mediaId = ?1 AND r.mediaType = ?2")
    double getAvgRatingToMedia(int id, MediaType mediaType);

    @Modifying
    @Query("DELETE FROM Rating r WHERE r.mediaId=:mediaId AND r.mediaType=:mediaType")
    void deleteAllByMediaIdAndMediaType(@Param("mediaId") int mediaId, MediaType mediaType);

    @Modifying
    @Query("DELETE FROM Rating r WHERE r.userId=:userId")
    void deleteByUserId(@Param("userId") String userId);

}
