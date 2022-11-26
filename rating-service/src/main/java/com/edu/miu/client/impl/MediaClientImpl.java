package com.edu.miu.client.impl;

import com.edu.miu.client.MediaClient;
import com.edu.miu.client.MovieClient;
import com.edu.miu.client.SeriesClient;
import com.edu.miu.enums.MediaType;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MediaClientImpl implements MediaClient {

    private final CircuitBreakerFactory seriesCircuitBreakerFactory;

    private final MovieClient movieClient;

    private final SeriesClient seriesClient;

    @Override
    public boolean isValidMedia(int id, MediaType mediaType) {
        return this.getMediaById(id, mediaType) != null;
    }

    @Override
    public Object getMediaById(int id, MediaType mediaType) {
        try {
            switch (mediaType) {
                case MOVIE:
                    return this.getMovieById(id);
                case TV_SERIES:
                    return this.getSeriesById(id);
                default:
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public Object getMovieById(int id) {
        CircuitBreaker circuitBreaker = seriesCircuitBreakerFactory.create("movie-fetching");
        return circuitBreaker.run(() -> movieClient.getMovieById(id), throwable -> null);
    }

    public Object getSeriesById(int id) {
        CircuitBreaker circuitBreaker = seriesCircuitBreakerFactory.create("series-fetching");
        return circuitBreaker.run(() -> seriesClient.getSeriesById(id), throwable -> null);
    }
}
