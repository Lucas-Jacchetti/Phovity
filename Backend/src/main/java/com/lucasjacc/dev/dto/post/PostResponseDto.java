package com.lucasjacc.dev.dto.post;

import java.time.LocalDateTime;

import com.lucasjacc.dev.dto.user.UserSummaryDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponseDto {
    private Long id;
    private LocalDateTime createdAt;
    private String postImgUrl;
    private String description;
    private String tag;
    private Long likeCount;
    private Boolean likedByMe;
    private Boolean savedByMe;

    private UserSummaryDto author;

}
