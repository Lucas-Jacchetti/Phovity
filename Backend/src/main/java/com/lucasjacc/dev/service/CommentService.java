package com.lucasjacc.dev.service;

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
import com.lucasjacc.dev.repository.UserRepository;

@Service
public class CommentService {
    private CommentRepository repository;
    private UserRepository userRepository;
    private PostRepository postRepository;

    public CommentService(CommentRepository repository, UserRepository userRepository, PostRepository postRepository){
        this.repository = repository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public List<CommentResponseDto> getAll(){
        return repository.findAll().stream().map(CommentMapper::toResponse).toList();
    }


    public CommentResponseDto create(CommentCreateDto dto){
        Comment comment = new Comment();
        User author = userRepository.findById(dto.getAuthorId()).orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado"));    
        Post post = postRepository.findById(dto.getPostId()).orElseThrow(() -> new ResourceNotFoundException("Post não encontrado"));
        comment.setText(dto.getText());
        comment.setAuthor(author);
        comment.setPost(post);

        Comment saved = repository.save(comment);
        return CommentMapper.toResponse(saved);
    }   

    public void deleteComment(Long id){
        repository.deleteById(id);
    }
}
