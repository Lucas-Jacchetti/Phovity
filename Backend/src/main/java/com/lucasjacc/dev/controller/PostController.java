package com.lucasjacc.dev.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lucasjacc.dev.dto.post.PostCreateDto;
import com.lucasjacc.dev.dto.post.PostResponseDto;
import com.lucasjacc.dev.service.PostService;

import tools.jackson.databind.ObjectMapper;

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
    public PostResponseDto getPost(@PathVariable Long id, @RequestParam Long userId){
        return service.getPost(id, userId);
    }

    @GetMapping("/user/{authorId}")
    public List<PostResponseDto> getPostByUser(@PathVariable Long authorId){
        return service.getByUser(authorId);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PostResponseDto create(@RequestPart("data") String data, @RequestPart("image") MultipartFile image) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        PostCreateDto dto = mapper.readValue(data, PostCreateDto.class);

        return service.create(dto, image);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deletePost(id);
    }
}
