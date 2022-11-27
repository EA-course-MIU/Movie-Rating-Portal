package com.edu.miu.service;

import com.edu.miu.dto.MediaDto;
import com.edu.miu.dto.UserDto;
import com.edu.miu.dto.CommentDto;

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
