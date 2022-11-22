package com.example.service.Impl;

import com.example.dto.CommentDto;
import com.example.dto.UserDto;
import com.example.entity.Comment;
import com.example.mapper.CommentMapper;
import com.example.repo.CommentRepo;
import com.example.service.CommentService;
import com.example.service.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepo commentRepo;

    private UserClient userClient;
    private final CommentMapper commentMapper;

    private final CircuitBreakerFactory circuitBreakerFactory;
    @Override
    @Transactional
    public List <CommentDto> findAll () {
       // return commentMapper.toDtos ((List <Comment>) commentRepo.findAll ());

        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("user-breaker");
        var users = circuitBreaker.run(() -> userClient.getAllUsers(),
                throwable -> getDefaultUsers());
        return ((List <Comment>) commentRepo.findAll ())
                .stream().map( r -> new CommentDto(r, (List<UserDto>)users)).collect(Collectors.toList());
    }
    public Iterable<UserDto> getDefaultUsers(){
        return new ArrayList<>();
    }

    @Override
    @Transactional
    public CommentDto findById (Integer id) {
        return commentMapper.toDto (commentRepo.findById (id).get ());
    }

    @Override
    @Transactional
    public CommentDto addComment (CommentDto commentDto) {
        Comment comment = commentMapper.toEntity (commentDto);
       commentRepo.save (comment);
       return  commentMapper.toDto (comment);
    }

    @Override
    @Transactional
    public CommentDto deleteComment (Integer id) {
        Comment comment = commentRepo.findById(id).get ();
        if(comment != null){
            commentRepo.deleteById (id);
        }
        return commentMapper.toDto (comment);
    }

    @Override
    @Transactional
    public CommentDto updateComment (Integer id,CommentDto commentDto)
    {
        Comment exist = commentRepo.findById (id).get();
        if(exist != null) {
            exist.setComment (commentDto.getComment ());
        }
//        if(commentDto.getUserId () > 0 && commentDto.getUserId () != exist.getIdUser ()){
//            exist.setIdUser (commentDto.getUserId ());
//        }
        if(commentDto.getMediaId () > 0 && commentDto.getMediaId () != exist.getMediaId ()){
            exist.setMediaId (commentDto.getMediaId ());
        }
        if(commentDto.getMediaType () != null && commentDto.getMediaType () != exist.getMediaType ()){
            exist.setMediaType (commentDto.getMediaType ());
        }
         return  commentMapper.toDto (exist);
    }

}
