package com.example.controller;

import com.example.dto.CommentDto;
import com.example.entity.Comment;
import com.example.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/users/{userId}")
    public List <CommentDto> findByUserId (@PathVariable("userId") int userId) {
    return commentService.findByUserId (userId);
    }
    @GetMapping("/media/{mediaId}")
    public List <CommentDto> findByMediaId  (@PathVariable("mediaId") int mediaId) {
        return commentService.findByMediaId (mediaId);
    }

    @GetMapping("/filter")
    public List <CommentDto> getByUserAndMedia (@RequestParam(required = false) int userId,
                                                @RequestParam(required = false) int mediaId) {
        return commentService.findByUserAndMedia (userId,mediaId);
    }
    @PostMapping("/users/{id}")
    public CommentDto addByUserId (@PathVariable("id") int id,@RequestBody CommentDto commentDto) {
        return commentService.addByUserId (id,commentDto);
    }

    @DeleteMapping("/users/{id}")
    public CommentDto deleteByUserId (@PathVariable("id") int id) {
        return commentService.deleteByUserId (id);
    }

    @PutMapping(path = "/users/{id}")
    public CommentDto updateByUserId (@PathVariable("id") Integer id,@RequestBody CommentDto commentDto)
    {
        return commentService.updateByUserId (id,commentDto);
    }


}
