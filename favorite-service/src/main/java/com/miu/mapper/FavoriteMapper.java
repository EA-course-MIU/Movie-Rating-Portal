package com.miu.mapper;

import com.miu.dto.FavoriteListDto;
import com.miu.entity.FavoriteList;
import com.miu.enums.MediaType;
import com.miu.service.MediaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FavoriteMapper extends Mapper<FavoriteList, FavoriteListDto> {
    @Autowired
    private MediaClient mediaClient;

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
            Object media = mediaClient.getMediaById(mediaId, mediaType);
            if(media != null) medias.add(media);
        });

        return favoriteListDto;
    }
}
