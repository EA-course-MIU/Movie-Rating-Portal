package com.example.controller;

import com.example.dto.CommentDto;
import com.example.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize ("hasRole('ROLE_USER')")
    @PostMapping("/users/{id}")
    public CommentDto addByUserId (@PathVariable("id") int id,@RequestBody CommentDto commentDto) {
        return commentService.addByUserId (id,commentDto);
    }

    @PreAuthorize ("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/all/users/{id}")
    public List<CommentDto> deleteAllByUserId (@PathVariable("id") int id) {
        return commentService.deleteAllByUserId (id);
    }
    @PreAuthorize ("hasRole('ROLE_USER')")
    @DeleteMapping("/users/{id}")
    public CommentDto deleteByUserId (@PathVariable("id") int id,@RequestParam(required = false) int commentId) {
        return commentService.deleteByUserId (id,commentId);
    }
    @PreAuthorize ("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @PutMapping(path = "/users/{id}")
    public CommentDto updateByUserId (@PathVariable("id") Integer id,@RequestBody CommentDto commentDto)
    {
        return commentService.updateByUserId (id,commentDto);
    }


}
