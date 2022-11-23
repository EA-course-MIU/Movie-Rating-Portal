package com.miu.entity;

import com.miu.enums.MediaType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteMedia {
    @EmbeddedId
    private FavoriteMediaKey favoriteMediaKey;
    @ManyToOne(fetch = javax.persistence.FetchType.LAZY)
    @MapsId("favoriteListId")
    private FavoriteList favoriteList;

    public FavoriteMedia(Integer mediaId, MediaType mediaType, FavoriteList favoriteList) {
        this.favoriteMediaKey = new FavoriteMediaKey(mediaType, mediaId, favoriteList.getId());
        this.favoriteList = favoriteList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteMedia that = (FavoriteMedia) o;
        FavoriteMediaKey thatFavoriteMediaKey = that.getFavoriteMediaKey();
        return thatFavoriteMediaKey.getMediaId() == favoriteMediaKey.getMediaId() && thatFavoriteMediaKey.getFavoriteListId() == favoriteMediaKey.getFavoriteListId() && thatFavoriteMediaKey.getMediaType() == favoriteMediaKey.getMediaType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(favoriteMediaKey, favoriteList);
    }
}
