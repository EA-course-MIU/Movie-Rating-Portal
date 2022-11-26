package com.edu.miu.mapper;


import com.edu.miu.dto.CommentDto;
import com.edu.miu.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentMapper {
    private final ModelMapper modelMapper;

    public CommentDto toDto(Comment comment) {
        return modelMapper.map(comment, CommentDto.class);
    }

    public List <CommentDto> toDtos(List<Comment> comments) {
        return comments.stream()
                .map(this::toDto)
                .toList();
    }

    public Comment toEntity(CommentDto commentDto) {
        return modelMapper.map(commentDto,Comment.class);
    }

    public List<Comment> toEntities(List<CommentDto> commentDtos) {
        return commentDtos.stream()
                .map(this::toEntity)
                .toList();
    }
}
