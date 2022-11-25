package com.miu.dto;

import com.miu.enums.MediaType;
import lombok.Data;

@Data
public class MediaDto {
    private Integer id;
    private MediaType type;

    public MediaDto(int id, MediaType type) {
        this.id = id;
        this.type = type;
    }
}
