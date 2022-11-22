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

    @GetMapping()
    public List <CommentDto> getAll () {
    return commentService.findAll ();
}

    @GetMapping(path = "/{id}")
    public CommentDto getCommentById (@PathVariable("id") Integer id) {
        return commentService.findById (id);
    }

    @PostMapping()
    public CommentDto addComment (@RequestBody CommentDto commentDto) {

        return commentService.addComment (commentDto);
    }

    @DeleteMapping(path = "/{id}")
    public CommentDto deleteComment (@PathVariable("id") Integer id)
    {
        return commentService.deleteComment (id);
    }

    @PutMapping(path = "/{id}")
    public CommentDto updateComment (@PathVariable("id") Integer id,@RequestBody CommentDto commentDto)
    {
        return commentService.updateComment (id,commentDto);
    }


}
