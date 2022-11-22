package com.example.dto;

import com.example.entity.Comment;
import com.example.enums.MediaType;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommentDto {
    private int id;
    private Date createdDate;
    private Date updateDate;
    private String comment;

//    private int userId;
    private UserDto user;
    private int mediaId;
    private MediaType mediaType;

    public CommentDto(Comment comment,List<UserDto> users){
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.user = users.stream().filter(u -> u.getId() == comment.getUserId ()).findFirst().orElse(null);
    }

}
