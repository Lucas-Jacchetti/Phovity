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
import com.lucasjacc.dev.repository.UserRepository;

@Service
public class LikeService {
    private final LikeRepository repository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    
    public LikeService(LikeRepository repository, UserRepository userRepository, PostRepository postRepository){
        this.repository = repository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public LikeResponseDto toggleLike(LikeCreateDto dto){
        User author = userRepository.findById(dto.getAuthorId()).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        boolean liked;
        long likeCount;

        Post post = postRepository.findById(dto.getPostId()).orElseThrow(() -> new ResourceNotFoundException("Post não encontrado"));

        Optional<Like> exists = repository.findByAuthorIdAndPostId(dto.getAuthorId(),dto.getPostId());
        if (exists.isPresent()) {
            repository.delete(exists.get());
            liked = false;
        }else {
            Like like = new Like();
            like.setAuthor(author);
            like.setPost(post);
            repository.save(like);
            liked = true;
        }
        
        likeCount = repository.countByPostId(dto.getPostId());

        return LikeMapper.toResponse(liked, likeCount);
    }

   
}
