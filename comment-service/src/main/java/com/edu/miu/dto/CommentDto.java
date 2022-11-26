package com.edu.miu.dto;

import com.edu.miu.enums.MediaType;
import lombok.Data;

import java.util.Date;

@Data
public class CommentDto {
    private int id;
    private Date createdDate;
    private Date updateDate;
    private String comment;

    private String userId;
    private int mediaId;
    private MediaType mediaType;


}
