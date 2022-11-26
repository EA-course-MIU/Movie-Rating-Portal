package com.miu.controller;

import com.miu.dto.FavoriteListDto;
import com.miu.dto.RequestFavoriteListDto;
import com.miu.dto.RequestFavoriteMedia;
import com.miu.entity.FavoriteList;
import com.miu.enums.MediaType;
import com.miu.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @PostMapping
    public FavoriteListDto createFavoriteList(@RequestBody RequestFavoriteListDto requestFavoriteListDto){
        return favoriteService.create(requestFavoriteListDto);
    }

    @GetMapping
    public List<FavoriteListDto> getAll() {
        return favoriteService.getAll();
    }

    @GetMapping("/{id}")
    public FavoriteListDto getFavoriteList(@PathVariable int id) {
        return favoriteService.getById(id);
    }

    @PutMapping("/{id}")
    public FavoriteListDto updateFavoriteList(@PathVariable int id, @RequestBody RequestFavoriteListDto requestFavoriteListDto) {
        return favoriteService.update(id, requestFavoriteListDto);
    }

    @DeleteMapping("/{id}")
    public void deleteFavoriteList(@PathVariable int id) {
        favoriteService.delete(id);
    }

    @PostMapping("/{id}/favorite-medias")
    public FavoriteListDto addFavoriteMedia(@PathVariable int id, @RequestBody RequestFavoriteMedia requestFavoriteMedia) {
        return favoriteService.addFavoriteMedia(id, requestFavoriteMedia);
    }

    @DeleteMapping("/{id}/favorite-medias")
    public FavoriteListDto removeFavoriteMedia(@PathVariable int id, @RequestParam("media-type") MediaType mediaType, @RequestParam("media-id") int mediaId) {
        return favoriteService.removeFavoriteMedia(id, new RequestFavoriteMedia(mediaId, mediaType));
    }
}
