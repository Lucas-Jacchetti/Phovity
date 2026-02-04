package com.lucasjacc.dev.dto.comment;

import lombok.Data;

@Data
public class CommentCreateDto {
    private String text;
    private Long authorId;
    private Long postId;
}
