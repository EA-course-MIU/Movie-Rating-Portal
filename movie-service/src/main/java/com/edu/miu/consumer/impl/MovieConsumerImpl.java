package com.edu.miu.consumer.impl;

import com.edu.miu.consumer.MovieConsumer;
import com.edu.miu.dto.FullMovieDto;
import com.edu.miu.dto.MediaRatingDto;
import com.edu.miu.dto.MovieDto;
import com.edu.miu.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieConsumerImpl implements MovieConsumer {

    private final MovieService movieService;

    @KafkaListener(
        topics = "avg-rating-movie-topic",
        containerFactory = "mediaRatingKafkaListenerContainerFactory",
        groupId = "update-avg-rating-topic")
    @Override
    public void receiveMessageFromRating(MediaRatingDto mediaRatingDto) {
        MovieDto movieDto = new MovieDto();
        movieDto.setAverageRating(mediaRatingDto.getAverageRating());
        System.out.println(movieService.updateMovie(mediaRatingDto.getMediaId(), movieDto));
    }

    @Override
    @RabbitListener(queues = {"rating-movie-queue"})
    public void receiveRabbitMessageFromRating(MediaRatingDto mediaRatingDto) {
        MovieDto movieDto = new MovieDto();
        movieDto.setAverageRating(mediaRatingDto.getAverageRating());
        System.out.println(movieService.updateMovie(mediaRatingDto.getMediaId(), movieDto));
    }


}
