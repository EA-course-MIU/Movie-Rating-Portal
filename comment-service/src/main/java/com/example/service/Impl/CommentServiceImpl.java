package com.example.service.Impl;

import com.example.dto.MediaDto;
import com.example.dto.UserDto;
import com.example.entity.Comment;
import com.example.dto.CommentDto;
import com.example.mapper.CommentMapper;
import com.example.repo.CommentRepo;
import com.example.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepo commentRepo;
    private final CommentMapper commentMapper;



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
    public List<CommentDto> deleteAllByUserId (int id) {
        List<Comment> comments = commentRepo.findAllByUserId (id);
        if(comments != null) {
            commentRepo.deleteAllByUserId (id);
        }
        else {
            throw new IllegalStateException ("User id: "+id+" does not exist");
        }
            return commentMapper.toDtos (comments);
    }

    @Override
    @Transactional
    public CommentDto deleteByUserId (Integer id,int commentId) {
        List<Comment> comments = commentRepo.findAllByUserId (id);
        Comment exist = comments.stream ().filter (c -> c.getId () == commentId).findAny ().orElse (null);

        if(exist == null) {
            throw new RuntimeException ("Comment of User Id:"+id+"is not exist");
        }
        else {
            commentRepo.deleteById (commentId);
        }
        return commentMapper.toDto (exist);
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
    @Transactional
    public CommentDto updateByUserId (Integer id,CommentDto commentDto) {
        Comment comment = commentMapper.toEntity (commentDto);
        List<Comment> comments = commentRepo.findAllByUserId (id);
        Comment exist = comments.stream ().filter (c -> c.getId () == comment.getId ()).findAny ().orElse (null);

        if(exist == null) {
            throw new RuntimeException ("Comment of User Id:"+id+"is not exist");
        }
        else {
            if(exist.getComment () !=null &&!Objects.equals (exist.getComment (),comment.getComment ())){
                exist.setComment (comment.getComment ());
            }

        }
        return commentMapper.toDto (exist);
    }



    @Override
    @Transactional
    public void deleteCommentByMediaId (MediaDto mediaDto) {
        commentRepo.deleteAllByMediaIdAndMediaType(mediaDto.getMediaId (), mediaDto.getMediaType());
    }

    @Override
    @Transactional
    public void deleteCommentByUserId (UserDto userDto) {
        commentRepo.deleteAllByUserId(userDto.getUserId ());

    }

}
