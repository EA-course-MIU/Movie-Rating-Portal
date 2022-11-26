package com.example.dto;

import com.example.enums.MediaType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaDto {
    private int mediaId;
    private MediaType mediaType;
}
