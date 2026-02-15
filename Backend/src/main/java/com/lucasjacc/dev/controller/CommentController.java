package com.lucasjacc.dev.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucasjacc.dev.dto.comment.CommentCreateDto;
import com.lucasjacc.dev.dto.comment.CommentResponseDto;
import com.lucasjacc.dev.model.User;
import com.lucasjacc.dev.repository.UserRepository;
import com.lucasjacc.dev.service.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private CommentService service;
    private UserRepository repository;

    public CommentController(CommentService service, UserRepository repository){
        this.service = service;
        this.repository = repository;
    }

    @GetMapping("/post/{postId}")
    public List<CommentResponseDto> getCommentByPost(@PathVariable Long postId){
        return service.getByPost(postId);
    }

    @PostMapping
    public CommentResponseDto create(@RequestBody CommentCreateDto dto, Authentication authentication){
        String email = authentication.getName();
        User author = (User) repository.findByEmail(email);

        return service.create(dto, author);
    }
    
    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id){
        service.deleteComment(id);
    }


}
