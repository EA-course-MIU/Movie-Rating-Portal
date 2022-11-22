package com.edu.miu.service;

import com.edu.miu.dto.MediaRatingDto;

public interface PublisherService {


    void sendMessageToMovieService(MediaRatingDto mediaRatingDto);

    void sendMessageToTvSeriesService(MediaRatingDto mediaRatingDto);

}
