package com.miu.dto;

import com.miu.enums.MediaType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestFavoriteMedia {
    private Integer mediaId;
    private MediaType mediaType;
    public boolean isValid(){
        System.out.println("===>"+(mediaId != null && mediaId > 0 && mediaType != null));
        return mediaId != null && mediaId > 0 && mediaType != null;
    }
}
