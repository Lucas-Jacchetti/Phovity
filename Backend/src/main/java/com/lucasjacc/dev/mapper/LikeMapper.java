package com.lucasjacc.dev.mapper;

import com.lucasjacc.dev.dto.like.LikeResponseDto;

public class LikeMapper {
    public static LikeResponseDto toResponse(boolean liked, long likeCount){
        LikeResponseDto dto = new LikeResponseDto();
        dto.setLikeCount(likeCount);
        dto.setLiked(liked);
        return dto;
    }
}
