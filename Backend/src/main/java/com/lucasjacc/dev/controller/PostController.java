package com.lucasjacc.dev.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lucasjacc.dev.dto.post.PostCreateDto;
import com.lucasjacc.dev.dto.post.PostResponseDto;
import com.lucasjacc.dev.model.User;
import com.lucasjacc.dev.repository.UserRepository;
import com.lucasjacc.dev.service.PostService;

import tools.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/posts")
public class PostController {
    private PostService service;
    private UserRepository repository;
    public PostController(PostService service, UserRepository repository){
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<PostResponseDto> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public PostResponseDto getPost(@PathVariable Long id, Authentication authentication){
        String email = authentication.getName();
        User user = (User) repository.findByEmail(email);
        return service.getPost(id, user.getId());
    }

    @GetMapping("/user/me")
    public List<PostResponseDto> getPostByUser(Authentication auth){
        String email = auth.getName();
        User user = (User) repository.findByEmail(email);
        
        if (user == null) {
            throw new RuntimeException("Usuário não encontrado");
        }
        return service.getByUser(user.getId());
    }

    @GetMapping("/user/saved")
    public List<PostResponseDto> getSavedPosts(Authentication auth){
        String email = auth.getName();
        User user = (User) repository.findByEmail(email);
        
        if (user == null) {
            throw new RuntimeException("Usuário não encontrado");
        }
        return service.getSavedPosts(user.getId());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PostResponseDto create(@RequestPart("data") String data, @RequestPart("image") MultipartFile image, Authentication authentication) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        PostCreateDto dto = mapper.readValue(data, PostCreateDto.class);

        String email = authentication.getName();
        User user = (User) repository.findByEmail(email);

        return service.create(dto, image, user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deletePost(id);
    }
}
