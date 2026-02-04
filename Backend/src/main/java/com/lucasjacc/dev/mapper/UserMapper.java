package com.lucasjacc.dev.mapper;

import com.lucasjacc.dev.dto.user.UserResponseDto;
import com.lucasjacc.dev.dto.user.UserSummaryDto;
import com.lucasjacc.dev.model.User;

public class UserMapper {
    
    public static UserResponseDto toResponse(User user){
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setBio(user.getBio());
        dto.setName(user.getName());
        dto.setProfileImgUrl(user.getProfileImgUrl());
        return dto;
    }

    public static UserSummaryDto toSummary(User user){
        UserSummaryDto dto = new UserSummaryDto();
        dto.setId(user.getId());
        dto.setProfileImageUrl(user.getProfileImgUrl());
        dto.setUsername(user.getName());
        return dto;
    }
}
