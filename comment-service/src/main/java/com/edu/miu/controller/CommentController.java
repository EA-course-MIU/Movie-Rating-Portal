package com.edu.miu.controller;

import com.edu.miu.dto.CommentDto;
import com.edu.miu.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/users/{userId}")
    public List <CommentDto> findByUserId (@PathVariable("userId") String userId) {
    return commentService.findByUserId (userId);
    }
    @GetMapping("/media/{mediaId}")
    public List <CommentDto> findByMediaId  (@PathVariable("mediaId") int mediaId) {
        return commentService.findByMediaId (mediaId);
    }

    @GetMapping("/filter")
    public List <CommentDto> getByUserAndMedia (@RequestParam(required = false) String userId,
                                                @RequestParam(required = false) int mediaId) {
        return commentService.findByUserAndMedia (userId,mediaId);
    }
    @PostMapping("/users/{id}")
    public CommentDto addByUserId (@PathVariable("id") String id,@RequestBody CommentDto commentDto) {
        return commentService.addByUserId (id,commentDto);
    }

    @DeleteMapping("/users/{id}")
    public List<CommentDto> deleteByUserId (@PathVariable("id") String id) {
        return commentService.deleteByUserId (id);
    }

    @PutMapping(path = "/users/{id}")
    public CommentDto updateByUserId (@PathVariable("id") String id,@RequestBody CommentDto commentDto)
    {
        return commentService.updateByUserId (id,commentDto);
    }


}
