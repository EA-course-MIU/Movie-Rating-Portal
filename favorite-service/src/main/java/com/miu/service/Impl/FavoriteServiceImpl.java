package com.miu.service.Impl;

import com.miu.dto.FavoriteListDto;
import com.miu.dto.RequestFavoriteListDto;
import com.miu.dto.RequestFavoriteMedia;
import com.miu.entity.FavoriteList;
import com.miu.entity.FavoriteMedia;
import com.miu.mapper.FavoriteMapper;
import com.miu.repo.FavoriteListRepo;
import com.miu.service.FavoriteService;
import com.miu.service.MediaClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteListRepo favoriteListRepo;
    private final FavoriteMapper favoriteMapper;

    private final MediaClient mediaClient;

    @Override
    public List<FavoriteListDto> getAll() {
        return favoriteMapper.toListDto(favoriteListRepo.findAll());
    }

    @Override
    public FavoriteListDto getById(int id) {
        return favoriteMapper.toDto(favoriteListRepo.findById(id).orElse(null));
    }

    @Transactional
    @Override
    public FavoriteListDto create(RequestFavoriteListDto requestFavoriteListDto) {
        var favoriteList = favoriteListRepo.save(new FavoriteList(requestFavoriteListDto.getTitle(), "1"));
        return favoriteMapper.toDto(favoriteList);
    }

    @Transactional
    @Override
    public FavoriteListDto update(int id, RequestFavoriteListDto requestFavoriteListDto) {
        var favoriteList = favoriteListRepo.findById(id).orElse(null);
        if (favoriteList == null) return null;
        favoriteList.setTitle(requestFavoriteListDto.getTitle());
        return favoriteMapper.toDto(favoriteList);
    }

    @Transactional
    @Override
    public FavoriteListDto addFavoriteMedia(int favoriteListId, RequestFavoriteMedia requestFavoriteMedia) {
        var favoriteList = favoriteListRepo.findById(favoriteListId).orElse(null);
        if (favoriteList == null) return null;
        if (!requestFavoriteMedia.isValid()) return null;
        if (!mediaClient.isValidMedia(requestFavoriteMedia.getMediaId(), requestFavoriteMedia.getMediaType())) {
            return favoriteMapper.toDto(favoriteList);
        }
        var newFavoriteMedia = new FavoriteMedia(requestFavoriteMedia.getMediaId(), requestFavoriteMedia.getMediaType(), favoriteList);
        List<FavoriteMedia> favoriteMedias = favoriteList.getFavoriteMediaList();
        boolean hasAdded = favoriteMedias.stream().anyMatch(f -> f.equals(newFavoriteMedia));
        if (!hasAdded) {
            favoriteMedias.add(newFavoriteMedia);
        }
        return favoriteMapper.toDto(favoriteList);
    }

    @Transactional
    @Override
    public FavoriteListDto removeFavoriteMedia(int favoriteListId, RequestFavoriteMedia requestFavoriteMedia) {
        var favoriteList = favoriteListRepo.findById(favoriteListId).orElse(null);
        if (favoriteList == null || !requestFavoriteMedia.isValid()) return null;
        favoriteList.getFavoriteMediaList().remove(new FavoriteMedia(requestFavoriteMedia.getMediaId(), requestFavoriteMedia.getMediaType(), favoriteList));
        return favoriteMapper.toDto(favoriteList);
    }

    @Transactional
    @Override
    public void delete(int id) {
        favoriteListRepo.deleteById(id);
    }

    @Override
    public List<FavoriteListDto> getAllByUserId(String userId) {
        return favoriteMapper.toListDto(favoriteListRepo.findAllByUserIdIs(userId));
    }
}
