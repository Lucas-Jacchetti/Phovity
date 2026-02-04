package com.lucasjacc.dev.mapper;

import com.lucasjacc.dev.dto.user.UserCreateDto;
import com.lucasjacc.dev.dto.user.UserResponseDto;
import com.lucasjacc.dev.dto.user.UserSummaryDto;
import com.lucasjacc.dev.model.User;

public class UserMapper {
    
    public static UserResponseDto toResponse(User user){
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setBio(user.getBio());
        dto.setName(user.getName());
        dto.setUserName(user.getUserName());
        dto.setEmail(user.getEmail());
        dto.setProfileImgUrl(user.getProfileImgUrl());
        dto.setCreatedAt(user.getCreatedAt().toString());
        return dto;
    }

    public static UserSummaryDto toSummary(User user){
        UserSummaryDto dto = new UserSummaryDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUserName());
        dto.setProfileImageUrl(user.getProfileImgUrl());
        dto.setUsername(user.getName());
        return dto;
    }

    public static User toEntity(UserCreateDto dto){
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setUserName(dto.getUserName());
        return user;
    }
}
