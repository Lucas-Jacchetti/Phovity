package com.lucasjacc.dev.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.lucasjacc.dev.dto.user.UserResponseDto;
import com.lucasjacc.dev.dto.user.UserUpdateDto;
import com.lucasjacc.dev.mapper.UserMapper;
import com.lucasjacc.dev.model.User;
import com.lucasjacc.dev.repository.UserRepository;
import com.lucasjacc.dev.exception.ResourceNotFoundException;

@Service
public class UserService {
    
    private UserRepository repository;
    public UserService(UserRepository repository){
        this.repository = repository;
    }

    public List<UserResponseDto> getAll(){
        return repository.findAll().stream().map(UserMapper::toResponse).toList();
    }

    public UserResponseDto getUser(Long id){
        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        return UserMapper.toResponse(user);
    }

    public void deleteUser(Long id){
        repository.deleteById(id);
    }

    public UserResponseDto updateUser(Long id, UserUpdateDto dto) {
        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        user.setBio(dto.getBio());
        user.setUserName(dto.getUserName());
        user.setProfileImgUrl(dto.getProfileImgUrl());
        repository.save(user);
        return UserMapper.toResponse(user);
    }
}
