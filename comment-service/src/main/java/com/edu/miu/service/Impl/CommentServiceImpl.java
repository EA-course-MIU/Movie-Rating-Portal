package com.edu.miu.service.Impl;

import com.edu.miu.dto.CommentDto;
import com.edu.miu.dto.MediaDto;
import com.edu.miu.dto.UserDto;
import com.edu.miu.entity.Comment;
import com.edu.miu.mapper.CommentMapper;
import com.edu.miu.repo.CommentRepo;
import com.edu.miu.service.CommentService;
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
    public List <CommentDto> findByUserId (String id) {
        return commentMapper.toDtos (commentRepo.findAllByUserId (id));
    }

    @Override
    public List <CommentDto> findByMediaId (int id) {
        return commentMapper.toDtos (commentRepo.findAllByMediaId (id));
    }

    @Override
    public List <CommentDto> findByUserAndMedia (String userId,int mediaId) {
        return commentMapper.toDtos (commentRepo.findAllByUserIdAndMediaId (userId,mediaId));
    }
    @Override
    @Transactional
    public List<CommentDto> deleteByUserId (String id) {
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
    public CommentDto addByUserId (String id,CommentDto commentDto) {
        Comment comment = commentMapper.toEntity (commentDto);
        comment.setUserId (id);
        commentRepo.save (comment);

        return commentMapper.toDto (comment);
    }
    @Override
    @Transactional
    public CommentDto updateByUserId (String id,CommentDto commentDto) {
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
        commentRepo.deleteAllByMediaIdAndMediaType(mediaDto.getId (), mediaDto.getMediaType());
    }

    @Override
    @Transactional
    public void deleteCommentByUserId (UserDto userDto) {
        commentRepo.deleteAllByUserId(userDto.getUserId ());

    }

}
