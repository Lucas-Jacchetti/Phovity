package com.lucasjacc.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasjacc.dev.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
