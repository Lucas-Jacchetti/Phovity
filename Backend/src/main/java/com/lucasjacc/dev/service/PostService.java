package com.lucasjacc.dev.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lucasjacc.dev.dto.post.PostCreateDto;
import com.lucasjacc.dev.dto.post.PostResponseDto;
import com.lucasjacc.dev.exception.ResourceNotFoundException;
import com.lucasjacc.dev.mapper.PostMapper;
import com.lucasjacc.dev.model.Post;
import com.lucasjacc.dev.model.User;
import com.lucasjacc.dev.repository.PostRepository;
import com.lucasjacc.dev.repository.UserRepository;

@Service
public class PostService {
    private PostRepository repository;
    private UserRepository userRepository;
    
    public PostService(PostRepository repository, UserRepository userRepository){
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public List<PostResponseDto> getAll(){
        return repository.findAll().stream().map(PostMapper::toResponse).toList();
    }

    public PostResponseDto getPost(Long id){
        Post post = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post não encontrado"));
        return PostMapper.toResponse(post);
    }

    public List<PostResponseDto> getByUser(Long userId) {
        return repository.findByAuthorId(userId)
                .stream()
                .map(PostMapper::toResponse)
                .toList();
    }

    public PostResponseDto create(PostCreateDto dto){
        User author = userRepository.findById(dto.getAuthorId()).orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado"));   
        Post post = new Post();
        post.setTag(dto.getTag());
        post.setPostImgUrl(dto.getPostImgUrl());
        post.setAuthor(author);
        post.setCreatedAt(LocalDateTime.now());

        Post saved = repository.save(post);
        return PostMapper.toResponse(saved);
    }

    public void deletePost(Long id){
        repository.deleteById(id);
    }
}
