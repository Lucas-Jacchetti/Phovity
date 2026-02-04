package com.lucasjacc.dev.mapper;

import com.lucasjacc.dev.dto.comment.CommentResponseDto;
import com.lucasjacc.dev.model.Comment;

public class CommentMapper {
    
    public static CommentResponseDto toResponse(Comment comment){
        CommentResponseDto dto = new CommentResponseDto();
        dto.setAuthor(UserMapper.toSummary(comment.getAuthor()));
        dto.setId(comment.getId());
        dto.setText(comment.getText());
        dto.setCreatedAt(comment.getCreatedAt());
        return dto;
    }
}
