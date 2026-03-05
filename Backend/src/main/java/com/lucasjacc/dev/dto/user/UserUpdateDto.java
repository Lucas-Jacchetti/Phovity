package com.lucasjacc.dev.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDto {
    private String userName;
    private String bio;
    private String profileImgUrl;
}
