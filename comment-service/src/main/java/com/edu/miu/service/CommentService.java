package com.edu.miu.service;

import com.edu.miu.dto.CommentDto;
import com.edu.miu.dto.MediaDto;
import com.edu.miu.dto.UserDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> findByUserId (String id);

    List<CommentDto> findByMediaId (int id);

    List<CommentDto> findByUserAndMedia (String userId,int mediaId);

    List<CommentDto> deleteByUserId (String id);

    CommentDto addByUserId (String id,CommentDto commentDto);

    CommentDto updateByUserId (String id, CommentDto commentDto);

    void deleteCommentByMediaId (MediaDto mediaDto);

    void deleteCommentByUserId (UserDto userDto);
}
