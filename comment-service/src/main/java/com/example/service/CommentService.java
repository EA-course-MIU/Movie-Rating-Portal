package com.example.service;

import com.example.dto.CommentDto;
import com.example.entity.Comment;

import java.util.List;

public interface CommentService {
    public List <CommentDto> findAll ();

    public CommentDto findById (Integer id);

    public CommentDto addComment (CommentDto commentDto);

    public CommentDto deleteComment (Integer id);

    public CommentDto updateComment (Integer id,CommentDto commentDto);
}
