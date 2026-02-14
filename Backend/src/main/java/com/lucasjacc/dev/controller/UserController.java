package com.lucasjacc.dev.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucasjacc.dev.dto.user.UserResponseDto;
import com.lucasjacc.dev.dto.user.UserUpdateDto;
import com.lucasjacc.dev.model.User;
import com.lucasjacc.dev.repository.UserRepository;
import com.lucasjacc.dev.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService service;
    private UserRepository repository;
    public UserController(UserService service, UserRepository repository){
        this.service = service;
        this.repository = repository;
    }

    @GetMapping("/me")
    public UserResponseDto getUser(Authentication auth){
        String email = auth.getName();
        User user = (User) repository.findByEmail(email);
        
        if (user == null) {
            throw new RuntimeException("Usuário não encontrado");
        }
        
        return service.getUser(user.getId());
    }

    @GetMapping
    public List<UserResponseDto> getAll(){
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteUser(id);
    }

    @PutMapping("/me")
    public UserResponseDto updateUser(Authentication auth, @RequestBody UserUpdateDto dto) {
        String email = auth.getName();
        User user = (User) repository.findByEmail(email);

        return service.updateUser(user.getId(), dto);
    }
}
