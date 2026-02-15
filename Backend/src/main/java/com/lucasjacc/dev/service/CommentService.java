package com.lucasjacc.dev.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lucasjacc.dev.dto.comment.CommentCreateDto;
import com.lucasjacc.dev.dto.comment.CommentResponseDto;
import com.lucasjacc.dev.exception.ResourceNotFoundException;
import com.lucasjacc.dev.mapper.CommentMapper;
import com.lucasjacc.dev.model.Comment;
import com.lucasjacc.dev.model.Post;
import com.lucasjacc.dev.model.User;
import com.lucasjacc.dev.repository.CommentRepository;
import com.lucasjacc.dev.repository.PostRepository;

@Service
public class CommentService {
    private CommentRepository repository;
    private PostRepository postRepository;

    public CommentService(CommentRepository repository, PostRepository postRepository){
        this.repository = repository;
        this.postRepository = postRepository;
    }

    public List<CommentResponseDto> getByPost(Long postId){
        return repository.findByPostId(postId).stream().map(CommentMapper::toResponse).toList();
    }


    public CommentResponseDto create(CommentCreateDto dto, User author){
        Comment comment = new Comment();
        Post post = postRepository.findById(dto.getPostId()).orElseThrow(() -> new ResourceNotFoundException("Post não encontrado"));
        comment.setText(dto.getText());
        comment.setAuthor(author);
        comment.setPost(post);
        comment.setCreatedAt(LocalDateTime.now());

        Comment saved = repository.save(comment);
        return CommentMapper.toResponse(saved);
    }   

    public void deleteComment(Long id){
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Comentário não encontrado");
        }
        repository.deleteById(id);
    }
}
