package com.miu.entity;

import com.miu.enums.MediaType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
public class FavoriteMediaKey implements Serializable {
    @Enumerated(EnumType.STRING)
    private MediaType mediaType;
    private int mediaId;
    private int favoriteListId;

    public FavoriteMediaKey(MediaType mediaType, int mediaId, int favoriteListId) {
        this.mediaType = mediaType;
        this.mediaId = mediaId;
        this.favoriteListId = favoriteListId;
    }

}
