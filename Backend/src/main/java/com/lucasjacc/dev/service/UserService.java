package com.lucasjacc.dev.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lucasjacc.dev.dto.user.UserCreateDto;
import com.lucasjacc.dev.dto.user.UserResponseDto;
import com.lucasjacc.dev.mapper.UserMapper;
import com.lucasjacc.dev.model.User;
import com.lucasjacc.dev.repository.UserRepository;

@Service
public class UserService {
    
    private UserRepository repository;

    public UserService(UserRepository repository){
        this.repository = repository;
    }

    public List<UserResponseDto> getAll(){
        return repository.findAll().stream().map(UserMapper::toResponse).toList();
    }

    public UserResponseDto getUser(long id){
        User user = repository.findById(id).orElse(null);
        return UserMapper.toResponse(user);
    }

    public UserResponseDto create(UserCreateDto dto){
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword()); // precisa de criptografia
        user.setCreatedAt(LocalDateTime.now());

        User saved = repository.save(user);
        return UserMapper.toResponse(saved);
    }   

    public void deleteUser(Long id){
        repository.deleteById(id);
    }
}
