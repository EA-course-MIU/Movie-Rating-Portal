package com.miu.dto;

import lombok.Data;

import java.util.List;

@Data
public class FavoriteListDto {
    private int id;
    private String title;
    private String userId;
    private List<Object> medias;
//    private List<FavoriteMediaDto> favoriteMediaList;
}
