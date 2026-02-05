package com.lucasjacc.dev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasjacc.dev.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUserName(String userName);
    Optional<User> findByEmail(String email);
}
