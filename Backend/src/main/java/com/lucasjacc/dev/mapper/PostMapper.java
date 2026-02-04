package com.lucasjacc.dev.mapper;

import java.time.LocalDateTime;

import com.lucasjacc.dev.dto.post.PostCreateDto;
import com.lucasjacc.dev.dto.post.PostResponseDto;
import com.lucasjacc.dev.model.Post;
import com.lucasjacc.dev.model.User;

public class PostMapper {
    
    public static PostResponseDto toResponse(Post post){
        PostResponseDto dto = new PostResponseDto();
        dto.setAuthor(UserMapper.toSummary(post.getAuthor()));
        dto.setPostImgUrl(post.getPostImgUrl());
        dto.setId(post.getId());
        dto.setDescription(post.getDescription());
        dto.setCratedAt(post.getCreatedAt().toString());
        dto.setCommentCount(post.getCommentCount());
        dto.setLikes(post.getLike());
        return dto;
    }

    public static Post toEntity(PostCreateDto dto, User author, String imageUrl){
        Post post = new Post();
        post.setAuthor(author);
        post.setDescription(dto.getDescription());
        post.setPostImgUrl(imageUrl);
        post.setCreatedAt(LocalDateTime.now());
        return post;
    }
}
