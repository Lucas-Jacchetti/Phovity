package com.lucasjacc.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasjacc.dev.model.User;
import com.lucasjacc.dev.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> getAll(){
        return repository.findAll();
    }

    public User getUser(long id){
        return repository.findById(id).orElse(null);
    }

    public User saveUser(User user){
        return repository.save(user);
    }

    public void deleteUser(Long id){
        repository.deleteById(id);
    }
}
