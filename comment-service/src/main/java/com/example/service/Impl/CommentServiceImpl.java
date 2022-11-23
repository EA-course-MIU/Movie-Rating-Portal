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
import java.util.Objects;

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
        else {
            throw new IllegalStateException ("User id: "+id+" does not exist");
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
        Comment exist = commentRepo.findByUserId (id);
        if(exist == null) {
            throw new RuntimeException ("Comment of User Id:"+id+"is not exist");
        }
        else {
            if(exist.getCreatedDate () !=null &&!Objects.equals (exist.getCreatedDate (),comment.getCreatedDate ())){
                exist.setCreatedDate (comment.getCreatedDate ());
            }
            if(exist.getUpdateDate () !=null &&!Objects.equals (exist.getUpdateDate (),comment.getUpdateDate ())){
                exist.setUpdateDate (comment.getUpdateDate ());
            }
            if(exist.getComment () !=null &&!Objects.equals (exist.getComment (),comment.getComment ())){
                exist.setComment (comment.getComment ());
            }
            if(exist.getMediaType () !=null &&!Objects.equals (exist.getMediaType (),comment.getMediaType ())){
                exist.setMediaType (comment.getMediaType ());
            }

        }
        return commentMapper.toDto (exist);
    }

}
