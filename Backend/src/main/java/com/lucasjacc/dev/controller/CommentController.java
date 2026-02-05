package com.lucasjacc.dev.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucasjacc.dev.dto.comment.CommentCreateDto;
import com.lucasjacc.dev.dto.comment.CommentResponseDto;
import com.lucasjacc.dev.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private CommentService service;
    public CommentController(CommentService service){
        this.service = service;
    }

    @GetMapping
    public List<CommentResponseDto> getComment(){
        return service.getAll();
    }

    @PostMapping
    public CommentResponseDto create(@RequestBody CommentCreateDto dto){
        return service.create(dto);
    }
    
    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id){
        service.deleteComment(id);
    }


}
