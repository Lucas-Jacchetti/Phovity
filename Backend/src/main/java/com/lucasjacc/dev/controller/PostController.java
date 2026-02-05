package com.lucasjacc.dev.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucasjacc.dev.dto.post.PostCreateDto;
import com.lucasjacc.dev.dto.post.PostResponseDto;
import com.lucasjacc.dev.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {
    private PostService service;
    public PostController(PostService service){
        this.service = service;
    }

    @GetMapping
    public List<PostResponseDto> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public PostResponseDto getPost(@PathVariable Long id){
        return service.getPost(id);
    }

    @GetMapping("/user/{authorId}")
    public List<PostResponseDto> getPostByUser(@PathVariable Long authorId){
        return service.getByUser(authorId);
    }

    @PostMapping
    public PostResponseDto create(@RequestBody PostCreateDto dto){
        return service.create(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deletePost(id);
    }
}
