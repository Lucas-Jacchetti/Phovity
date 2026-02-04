package com.lucasjacc.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasjacc.dev.model.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
    
}
