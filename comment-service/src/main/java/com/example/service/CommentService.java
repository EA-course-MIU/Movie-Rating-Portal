package com.example.service;

import com.example.dto.CommentDto;
import com.example.entity.Comment;

import java.util.List;

public interface CommentService {
    List<CommentDto> findByUserId (int id);

    List<CommentDto> findByMediaId (int id);

    List<CommentDto> findByUserAndMedia (int userId,int mediaId);

    CommentDto deleteByUserId (int id);

    CommentDto addByUserId (int id,CommentDto commentDto);

    CommentDto updateByUserId (Integer id,CommentDto commentDto);
}
