package com.lucasjacc.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasjacc.dev.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    
}
