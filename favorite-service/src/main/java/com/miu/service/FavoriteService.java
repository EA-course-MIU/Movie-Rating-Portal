package com.miu.service;

import com.miu.dto.FavoriteListDto;
import com.miu.dto.RequestFavoriteListDto;
import com.miu.dto.RequestFavoriteMedia;

import java.util.List;

public interface FavoriteService {
    List<FavoriteListDto> getAll();
    FavoriteListDto getById(int id, String userId);
    FavoriteListDto create(RequestFavoriteListDto requestFavoriteListDto, String userId);
    FavoriteListDto update(int id, RequestFavoriteListDto requestFavoriteListDto, String userId);
    FavoriteListDto addFavoriteMedia(int favoriteListId, RequestFavoriteMedia requestFavoriteMedia, String userId);
    FavoriteListDto removeFavoriteMedia(int favoriteListId, RequestFavoriteMedia requestFavoriteMedia, String userId);
    void delete(int id, String userId);
    List<FavoriteListDto> getAllByUserId(String userId);
}
