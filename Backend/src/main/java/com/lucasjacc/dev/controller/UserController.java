package com.lucasjacc.dev.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucasjacc.dev.dto.user.UserResponseDto;
import com.lucasjacc.dev.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService service;
    public UserController(UserService service){
        this.service = service;
    }

    @GetMapping("/{id}")
    public UserResponseDto getUser(@PathVariable Long id){
        return service.getUser(id);
    }

    @GetMapping
    public List<UserResponseDto> getAll(){
        return service.getAll();
    }

    // @PostMapping
    // public UserResponseDto create(@RequestBody UserCreateDto dto){
    //     return service.create(dto);
    // }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteUser(id);
    }
}
