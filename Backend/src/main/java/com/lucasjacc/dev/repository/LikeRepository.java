package com.lucasjacc.dev.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasjacc.dev.model.Like;

public interface LikeRepository extends JpaRepository<Like, Long>{
    Optional<Like> findByAuthorIdAndPostId(Long authorId, Long postId);

    long countByPostId(Long postId);
}
