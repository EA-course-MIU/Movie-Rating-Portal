package com.edu.miu.service;

import com.edu.miu.dto.MediaRatingDto;

public interface MovieConsumer {

    void receiveMessageFromRating(MediaRatingDto mediaRatingDto);


    void receiveRabbitMessageFromRating(MediaRatingDto mediaRatingDto);
}
