package com.miu.service.Impl;

import com.miu.enums.MediaType;
import com.miu.service.MediaClient;
import com.miu.service.MovieClient;
import com.miu.service.SeriesClient;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MediaClientImpl implements MediaClient {
    private final CircuitBreakerFactory seriesCircuitBreakerFactory;

    private final MovieClient movieClient;
    private final SeriesClient seriesClient;

    @Override
    public boolean isValidMedia(int id, MediaType mediaType) {
        return this.getMediaById(id, mediaType) == null;
    }

    @Override
    public Object getMediaById(int id, MediaType mediaType) {
        CircuitBreaker circuitBreaker = seriesCircuitBreakerFactory.create("media-fetching");
        return circuitBreaker.run(() -> {
            try {
                switch (mediaType) {
                    case MOVIE:
                        return movieClient.getMovieById(id);
                    case TV_SERIES:
                        return seriesClient.getSeriesById(id);
                    default:
                        return null;
                }
            } catch (Exception e) {
                return null;
            }
        }, throwable -> null);
    }
}
