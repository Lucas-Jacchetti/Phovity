package com.lucasjacc.dev.dto.user;


import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String name;
    private String bio;
    private String profileImgUrl;
}
