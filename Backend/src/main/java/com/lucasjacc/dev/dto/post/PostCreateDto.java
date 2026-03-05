package com.lucasjacc.dev.dto.post;

import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
public class PostCreateDto {
    private String description;
    private String tag;
    private String imgUrl;
}
