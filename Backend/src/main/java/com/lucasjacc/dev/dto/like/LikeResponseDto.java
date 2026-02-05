package com.lucasjacc.dev.dto.like;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeResponseDto {
    private Boolean liked;
    private long likeCount;
}
