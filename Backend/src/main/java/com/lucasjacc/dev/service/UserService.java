package com.lucasjacc.dev.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.lucasjacc.dev.dto.user.UserCreateDto;
import com.lucasjacc.dev.dto.user.UserResponseDto;
import com.lucasjacc.dev.mapper.UserMapper;
import com.lucasjacc.dev.model.User;
import com.lucasjacc.dev.repository.UserRepository;
import com.lucasjacc.dev.exception.ResourceNotFoundException;

@Service
public class UserService {
    
    private UserRepository repository;
    private PasswordEncoder passwordEncoder;
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponseDto> getAll(){
        return repository.findAll().stream().map(UserMapper::toResponse).toList();
    }

    public UserResponseDto getUser(Long id){
        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        return UserMapper.toResponse(user);
    }

    public UserResponseDto create(UserCreateDto dto){
        if (repository.findByUserName(dto.getUserName()).isPresent()) {
            throw new RuntimeException("Username já existe");
        }
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email já existe");
        }
        
        User user = UserMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        User saved = repository.save(user);
        return UserMapper.toResponse(saved);
    }   

    public void deleteUser(Long id){
        repository.deleteById(id);
    }
}
