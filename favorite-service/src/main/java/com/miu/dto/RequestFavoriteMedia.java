package com.miu.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    public boolean isValid(){
        return mediaId != null && mediaId > 0 && mediaType != null;
    }
}
