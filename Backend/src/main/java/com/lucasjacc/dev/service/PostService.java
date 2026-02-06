package com.lucasjacc.dev.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lucasjacc.dev.dto.post.PostCreateDto;
import com.lucasjacc.dev.dto.post.PostResponseDto;
import com.lucasjacc.dev.exception.ResourceNotFoundException;
import com.lucasjacc.dev.mapper.PostMapper;
import com.lucasjacc.dev.model.Post;
import com.lucasjacc.dev.model.User;
import com.lucasjacc.dev.repository.LikeRepository;
import com.lucasjacc.dev.repository.PostRepository;
import com.lucasjacc.dev.repository.UserRepository;

@Service
public class PostService {
    private PostRepository repository;
    private UserRepository userRepository;
    private LikeRepository likeRepository;
    
    public PostService(PostRepository repository, UserRepository userRepository, LikeRepository likeRepository){
        this.repository = repository;
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
    }

    public List<PostResponseDto> getAll(){
        return repository.findAll().stream().map(PostMapper::toResponse).toList();
    }

    public PostResponseDto getPost(Long id, Long postId){
        Post post = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post não encontrado"));

        long countLikes = likeRepository.countByPostId(postId);
        boolean likedByMe = likeRepository.existsByPostIdAndAuthorId(id, postId);

        PostResponseDto dto = PostMapper.toResponse(post);
        dto.setLikeCount(countLikes);
        dto.setLikedByMe(likedByMe);

        return dto;
    }

    public List<PostResponseDto> getByUser(Long userId) {
        return repository.findByAuthorId(userId)    
                .stream()
                .map(PostMapper::toResponse)
                .toList();
    }

    public PostResponseDto create(PostCreateDto dto, MultipartFile image){
        User author = userRepository.findById(dto.getAuthorId()).orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado"));   
        Post post = new Post();
        post.setDescription(dto.getDescription());
        post.setTag(dto.getTag());
        post.setAuthor(author);
        post.setCreatedAt(LocalDateTime.now());

        if (image.isEmpty())
            throw new IllegalArgumentException("Imagem obrigatória");

        if (!image.getContentType().startsWith("image/"))
            throw new IllegalArgumentException("Arquivo inválido");

        String imageUrl = FileStorageService.save(image);
        post.setPostImgUrl(imageUrl);
        
        Post saved = repository.save(post);
        return PostMapper.toResponse(saved);
    }

    public void deletePost(Long id){
        repository.deleteById(id);
    }
}
