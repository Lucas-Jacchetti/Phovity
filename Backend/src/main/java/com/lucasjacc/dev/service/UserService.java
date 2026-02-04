package com.lucasjacc.dev.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.lucasjacc.dev.dto.user.UserCreateDto;
import com.lucasjacc.dev.dto.user.UserResponseDto;
import com.lucasjacc.dev.mapper.UserMapper;
import com.lucasjacc.dev.model.User;
import com.lucasjacc.dev.repository.UserRepository;

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

    public UserResponseDto getUser(long id){
        User user = repository.findById(id).orElse(null);
        return UserMapper.toResponse(user);
    }

    public UserResponseDto create(UserCreateDto dto){
        if (repository.findByUsername(dto.getUserName()).isPresent()) {
            return null;
        }
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            return null;
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
