package com.lucasjacc.dev.dto.comment;

import java.time.LocalDateTime;

import com.lucasjacc.dev.dto.user.UserSummaryDto;

import lombok.Data;

@Data
public class CommentResponseDto {
    private Long id;
    private String text;
    private LocalDateTime createdAt;

    private UserSummaryDto author;

}
