package com.example.dto;

import com.example.enums.MediaType;
import lombok.Data;

import java.util.Date;

@Data
public class CommentDto {
    private int id;
    private Date createdDate;
    private Date updateDate;
    private String comment;

    private int userId;
    private int mediaId;
    private MediaType mediaType;


}
