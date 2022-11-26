package com.edu.miu.service;

import com.edu.miu.dto.RatingDto;
import com.edu.miu.dto.RatingReportDto;
import com.edu.miu.enums.MediaType;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface RatingService {

    RatingDto getById(int id);

    List<RatingDto> getAll();

    List<RatingReportDto> getRatingReportForMedia(int id);


    double getAvgRatingToMedia(int mediaId, MediaType mediaType);

    RatingDto addRating(RatingDto ratingDto);

    RatingDto addRatingByUser(String userId, RatingDto ratingDto);

    RatingDto updateRating(int id, RatingDto rating);

    RatingDto updateRatingByUser(String userId, RatingDto ratingDto);

    RatingDto deleteRating(int id);

    void deleteRatingByMedia(int mediaId, MediaType mediaType);

    RatingDto deleteRatingByUser(String userId, int mediaId, MediaType mediaType);

    void deleteAllRatingByUserId(String id);
    
}
