package com.lucasjacc.dev.dto.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateDto {
    private String text;
    private Long authorId;
    private Long postId;
}
