package com.edu.miu.dto.message;

import com.edu.miu.enums.MediaType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaDto {

    private int mediaId;

    private MediaType mediaType;

}
