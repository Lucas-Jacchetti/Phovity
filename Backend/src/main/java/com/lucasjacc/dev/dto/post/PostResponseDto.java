package com.lucasjacc.dev.dto.post;

import com.lucasjacc.dev.dto.user.UserSummaryDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponseDto {
    private Long id;
    private String cratedAt;
    private String postImgUrl;
    private String description;
    private String tag;

    private UserSummaryDto author;

}
