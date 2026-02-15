package com.lucasjacc.dev.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lucasjacc.dev.dto.like.LikeCreateDto;
import com.lucasjacc.dev.dto.like.LikeResponseDto;
import com.lucasjacc.dev.exception.ResourceNotFoundException;
import com.lucasjacc.dev.mapper.LikeMapper;
import com.lucasjacc.dev.model.Like;
import com.lucasjacc.dev.model.Post;
import com.lucasjacc.dev.model.User;
import com.lucasjacc.dev.repository.LikeRepository;
import com.lucasjacc.dev.repository.PostRepository;

@Service
public class LikeService {
    private final LikeRepository repository;
    private final PostRepository postRepository;
    
    public LikeService(LikeRepository repository, PostRepository postRepository){
        this.repository = repository;
        this.postRepository = postRepository;
    }

    public LikeResponseDto toggleLike(LikeCreateDto dto, User user){

        boolean liked;
        long likeCount;

        Post post = postRepository.findById(dto.getPostId()).orElseThrow(() -> new ResourceNotFoundException("Post não encontrado"));

        Optional<Like> exists = repository.findByAuthorIdAndPostId(user.getId(),dto.getPostId());
        if (exists.isPresent()) {
            repository.delete(exists.get());
            liked = false;
        }else {
            Like like = new Like();
            like.setAuthor(user);
            like.setPost(post);
            repository.save(like);
            liked = true;
        }
        
        likeCount = repository.countByPostId(dto.getPostId());

        return LikeMapper.toResponse(liked, likeCount);
    }

   
}
