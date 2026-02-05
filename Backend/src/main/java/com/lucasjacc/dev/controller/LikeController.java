package com.lucasjacc.dev.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucasjacc.dev.dto.like.LikeCreateDto;
import com.lucasjacc.dev.dto.like.LikeResponseDto;
import com.lucasjacc.dev.service.LikeService;

@RestController
@RequestMapping("/likes")
public class LikeController {
    private LikeService service;

    public LikeController(LikeService service){
        this.service = service;
    }

    @PostMapping
    public LikeResponseDto toggle(@RequestBody LikeCreateDto dto){
        return service.toggleLike(dto);
    }
}
