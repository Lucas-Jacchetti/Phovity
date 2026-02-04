package com.lucasjacc.dev.dto.user;

import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
public class UserCreateDto {
    private String name;
    private String email;
    private String password;
}
