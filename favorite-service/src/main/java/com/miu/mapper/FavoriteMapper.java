package com.miu.mapper;

import com.miu.dto.FavoriteListDto;
import com.miu.entity.FavoriteList;
import com.miu.enums.MediaType;
import com.miu.service.MovieClient;
import com.miu.service.SeriesClient;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FavoriteMapper extends Mapper<FavoriteList, FavoriteListDto> {
    @Autowired
    private SeriesClient seriesClient;
    @Autowired
    private MovieClient movieClient;

    public FavoriteMapper() {
        super(FavoriteList.class, FavoriteListDto.class);
    }

    @Override
    public FavoriteListDto toDto(FavoriteList t) {
        FavoriteListDto favoriteListDto = super.toDto(t);
        List<Object> medias = new ArrayList<>();
        favoriteListDto.setMedias(medias);
        t.getFavoriteMediaList().stream().forEach(f -> {
            MediaType mediaType = f.getFavoriteMediaKey().getMediaType();
            int mediaId = f.getFavoriteMediaKey().getMediaId();
            if (mediaType == MediaType.TV_SERIES) {
                medias.add(seriesClient.getSeriesById(mediaId));
            } else if (mediaType == MediaType.MOVIE) {
                medias.add(movieClient.getMovieById(mediaId));
            }
        });

        return favoriteListDto;
    }
}
