package com.edu.miu.service;

import com.edu.miu.dto.RatingDto;
import com.edu.miu.dto.RatingReportDto;
import com.edu.miu.enums.MediaType;

import java.util.List;

public interface RatingService {

    RatingDto getById(int id);

    List<RatingDto> getAll();

    List<RatingReportDto> getRatingReportForMedia(int id);


    double getAvgRatingToMedia(int mediaId, MediaType mediaType);

    RatingDto addRating(RatingDto rating);

    RatingDto updateRating(int id, RatingDto rating);

    RatingDto deleteRating(int id);


    void deleteRatingByMediaId(RatingDto rating);
    
}
