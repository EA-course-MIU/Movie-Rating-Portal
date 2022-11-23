package com.miu.service;

import com.miu.dto.FavoriteListDto;
import com.miu.dto.RequestFavoriteListDto;
import com.miu.dto.RequestFavoriteMedia;

import java.util.List;

public interface FavoriteService {
    List<FavoriteListDto> getAll();
    FavoriteListDto getById(int id);
    FavoriteListDto create(RequestFavoriteListDto requestFavoriteListDto);
    FavoriteListDto update(int id, RequestFavoriteListDto requestFavoriteListDto);
    FavoriteListDto addFavoriteMedia(int favoriteListId, RequestFavoriteMedia requestFavoriteMedia);
    FavoriteListDto removeFavoriteMedia(int favoriteListId, RequestFavoriteMedia requestFavoriteMedia);
    void delete(int id);
    List<FavoriteListDto> getAllByUserId(String userId);
}
