package com.edu.miu.consumer;

import com.edu.miu.dto.MediaRatingDto;
import com.edu.miu.dto.RatingDto;

public interface RatingConsumer {

    void receiveMessageFromMovie(RatingDto ratingDto);


    void receiveRabbitMessageFromMovie(RatingDto ratingDto);

}
