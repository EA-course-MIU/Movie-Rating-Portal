package com.example.service.Impl;

import com.example.dto.CommentDto;
import com.example.dto.UserDto;
import com.example.entity.Comment;
import com.example.mapper.CommentMapper;
import com.example.repo.CommentRepo;
import com.example.service.CommentService;
import com.example.service.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepo commentRepo;

    private UserClient userClient;
    private final CommentMapper commentMapper;

    private final CircuitBreakerFactory circuitBreakerFactory;

    @Override
    public List <CommentDto> findByUserId (int id) {
        return commentMapper.toDtos (commentRepo.findAllByUserId (id));
    }

    @Override
    public List <CommentDto> findByMediaId (int id) {
        return commentMapper.toDtos (commentRepo.findAllByMediaId (id));
    }

    @Override
    public List <CommentDto> findByUserAndMedia (int userId,int mediaId) {
        return commentMapper.toDtos (commentRepo.findAllByUserIdAndMediaId (userId,mediaId));
    }
    @Override
    @Transactional
    public CommentDto deleteByUserId (int id) {
        Comment comment = commentRepo.findByUserId (id);
        if(comment != null) {
            commentRepo.deleteByUserId (id);
        }
            return commentMapper.toDto (comment);
    }

    @Override
    @Transactional
    public CommentDto addByUserId (int id,CommentDto commentDto) {
        Comment comment = commentMapper.toEntity (commentDto);
        comment.setUserId (id);
        commentRepo.save (comment);

        return commentMapper.toDto (comment);
    }
    @Override
    public CommentDto updateByUserId (Integer id,CommentDto commentDto) {
        Comment comment = commentMapper.toEntity (commentDto);
        if(comment != null) {
            comment.setUserId (id);
            commentRepo.save (comment);
        }
        return commentMapper.toDto (comment);
    }

}
