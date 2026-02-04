package com.lucasjacc.dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucasjacc.dev.model.User;
import com.lucasjacc.dev.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return service.getUser(id);
    }

    @GetMapping
    public List<User> getAll(){
        return service.getAll();
    }

    @PostMapping
    public User create(@RequestBody User user){
        return service.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteUser(id);
    }
}
