package com.edu.miu.dto;

import com.edu.miu.enums.MediaType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaDto {
    private Integer id;
    private MediaType mediaType;
}
