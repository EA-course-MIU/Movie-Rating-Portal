package com.example.service;

import com.example.dto.MediaDto;
import com.example.dto.UserDto;
import com.example.dto.CommentDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> findByUserId (int id);

    List<CommentDto> findByMediaId (int id);

    List<CommentDto> findByUserAndMedia (int userId,int mediaId);

    List<CommentDto> deleteAllByUserId (int id);

    CommentDto addByUserId (int id,CommentDto commentDto);

    CommentDto updateByUserId (Integer id,CommentDto commentDto);
    CommentDto deleteByUserId (Integer id,int commentId);

    void deleteCommentByMediaId (MediaDto mediaDto);

    void deleteCommentByUserId (UserDto userDto);
}
