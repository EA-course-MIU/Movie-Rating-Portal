package com.miu.repo;

import com.miu.entity.FavoriteMedia;
import com.miu.enums.MediaType;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteMediaRepo extends CrudRepository<FavoriteMedia, Integer> {
    @Query("DELETE FROM FavoriteMedia fm WHERE fm.favoriteMediaKey.mediaId=:mediaId AND fm.favoriteMediaKey.mediaType=:mediaType")
    public void deleteAllByMediaId(@Param("mediaType") MediaType mediaType, @Param("mediaId") int mediaId);
}
