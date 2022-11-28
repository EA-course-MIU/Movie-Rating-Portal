package com.miu.service.Impl;

import com.miu.dto.FavoriteListDto;
import com.miu.dto.RequestFavoriteListDto;
import com.miu.dto.RequestFavoriteMedia;
import com.miu.entity.FavoriteList;
import com.miu.entity.FavoriteMedia;
import com.miu.exception.BadRequestException;
import com.miu.exception.ResourceNotFoundException;
import com.miu.mapper.FavoriteMapper;
import com.miu.repo.FavoriteListRepo;
import com.miu.repo.FavoriteMediaRepo;
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

    private final FavoriteMediaRepo favoriteMediaRepo;
    private final FavoriteMapper favoriteMapper;

    private final MediaClient mediaClient;

    @Override
    public List<FavoriteListDto> getAll() {
        return favoriteMapper.toListDto(favoriteListRepo.findAll());
    }

    @Override
    public FavoriteListDto getById(int id, String userId) {
        var favoriteList = favoriteListRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Favorite list not found"));
        return favoriteMapper.toDto(favoriteList);
    }

    @Transactional
    @Override
    public FavoriteListDto create(RequestFavoriteListDto requestFavoriteListDto, String userId) {
        var favoriteList = favoriteListRepo.save(new FavoriteList(requestFavoriteListDto.getTitle(), userId));
        return favoriteMapper.toDto(favoriteList);
    }

    @Transactional
    @Override
    public FavoriteListDto update(int id, RequestFavoriteListDto requestFavoriteListDto, String userId) {
        var favoriteList = favoriteListRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Favorite list not found"));
        if (!favoriteList.getUserId().equals(userId))
            throw new BadRequestException("You cannot update this favorite list");
        favoriteList.setTitle(requestFavoriteListDto.getTitle());
        return favoriteMapper.toDto(favoriteList);
    }

    @Transactional
    @Override
    public FavoriteListDto addFavoriteMedia(int favoriteListId, RequestFavoriteMedia requestFavoriteMedia, String userId) {
        if (!requestFavoriteMedia.isValid()) throw new BadRequestException("Invalid request");
        var favoriteList = favoriteListRepo.findById(favoriteListId).orElseThrow(() -> new ResourceNotFoundException("Favorite list not found"));
        if (!favoriteList.getUserId().equals(userId)) throw new BadRequestException("You cannot add favorite media to this list");
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
    public FavoriteListDto removeFavoriteMedia(int favoriteListId, RequestFavoriteMedia requestFavoriteMedia, String userId) {
        var favoriteList = favoriteListRepo.findById(favoriteListId).orElseThrow(() -> new ResourceNotFoundException("Favorite list not found"));
        if (!requestFavoriteMedia.isValid()) throw new BadRequestException("Invalid request");
        if(!favoriteList.getUserId().equals(userId)) throw new BadRequestException("You cannot remove favorite media from this list");
        var deletingFavoriteMedia = new FavoriteMedia(requestFavoriteMedia.getMediaId(), requestFavoriteMedia.getMediaType(), favoriteList);
        favoriteMediaRepo.delete(deletingFavoriteMedia);
        favoriteList.getFavoriteMediaList().remove(deletingFavoriteMedia);
//        favoriteList.setFavoriteMediaList(favoriteList.getFavoriteMediaList());
        return favoriteMapper.toDto(favoriteList);
    }

    @Transactional
    @Override
    public void delete(int id, String userId) {
        var favoriteList = favoriteListRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Favorite list not found"));
        if (!favoriteList.getUserId().equals(userId)) throw new BadRequestException("You cannot delete this favorite list");
        favoriteListRepo.deleteById(id);
    }

    @Override
    public List<FavoriteListDto> getAllByUserId(String userId) {
        return favoriteMapper.toListDto(favoriteListRepo.findAllByUserIdIs(userId));
    }
}
