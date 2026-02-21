package com.lucasjacc.dev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import com.lucasjacc.dev.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUserName(String userName);
    UserDetails findByEmail(String email);

    @Query("""
        SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END
        FROM User u JOIN u.savedPosts p
        WHERE u.id = :userId AND p.id = :postId
        """)
    boolean existsSavedPost(Long userId, Long postId);
}
