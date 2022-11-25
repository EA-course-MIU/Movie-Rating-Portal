package com.edu.miu.publisher;

import com.edu.miu.dto.RatingDto;

public interface MoviePublisher {

    void sendRemovedMovieMessage(RatingDto ratingDto);

}
