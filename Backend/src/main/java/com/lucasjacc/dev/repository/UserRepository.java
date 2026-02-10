package com.lucasjacc.dev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.lucasjacc.dev.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUserName(String userName);
    UserDetails findByEmail(String email);
}
