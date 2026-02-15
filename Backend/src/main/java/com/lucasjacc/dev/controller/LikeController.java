package com.lucasjacc.dev.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucasjacc.dev.dto.like.LikeCreateDto;
import com.lucasjacc.dev.dto.like.LikeResponseDto;
import com.lucasjacc.dev.model.User;
import com.lucasjacc.dev.repository.UserRepository;
import com.lucasjacc.dev.service.LikeService;

@RestController
@RequestMapping("/likes")
public class LikeController {
    private LikeService service;
    private UserRepository repository;

    public LikeController(LikeService service, UserRepository repository){
        this.service = service;
        this.repository = repository;
    }

    @PostMapping
    public LikeResponseDto toggle(@RequestBody LikeCreateDto dto, Authentication authentication){
        String email = authentication.getName();
        User user = (User) repository.findByEmail(email);
        return service.toggleLike(dto, user);
    }
}
