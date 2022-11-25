package com.miu.service.Impl;

import com.miu.enums.MediaType;
import com.miu.service.MediaClient;
import com.miu.service.MovieClient;
import com.miu.service.SeriesClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MediaClientImpl implements MediaClient {

    private final MovieClient movieClient;
    private final SeriesClient seriesClient;
    @Override
    public boolean isValidMedia(int id, MediaType mediaType) {
        try{
            switch (mediaType){
                case MOVIE:
                    movieClient.getMovieById(id);
                    break;
                case TV_SERIES:
                    seriesClient.getSeriesById(id);
                    break;
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
