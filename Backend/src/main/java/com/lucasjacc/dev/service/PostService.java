package com.lucasjacc.dev.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.lucasjacc.dev.dto.post.PostCreateDto;
import com.lucasjacc.dev.dto.post.PostResponseDto;
import com.lucasjacc.dev.dto.save.SaveResponseDto;
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
    private LikeRepository likeRepository;
    private UserRepository userRepository;
    private PostRepository postRepository;
    
    public PostService(PostRepository repository, LikeRepository likeRepository, UserRepository userRepository, PostRepository postRepository){
        this.repository = repository;
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public List<PostResponseDto> getAll(){
        return repository.findAll().stream().map(PostMapper::toResponse).toList();
    }

    public PostResponseDto getPost(Long postId, Long userId){
        Post post = repository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post não encontrado"));

        long countLikes = likeRepository.countByPostId(postId);
        boolean likedByMe = likeRepository.existsByPostIdAndAuthorId(postId, userId);   

        boolean savedByMe = userRepository.existsSavedPost(userId, postId);

        PostResponseDto dto = PostMapper.toResponse(post);
        dto.setLikeCount(countLikes);
        dto.setLikedByMe(likedByMe);
        dto.setSavedByMe(savedByMe);

        return dto;
    }

    public List<PostResponseDto> getSavedPosts(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        return user.getSavedPosts()
            .stream()
            .map(post -> {
                PostResponseDto dto = PostMapper.toResponse(post);
                dto.setSavedByMe(true);
                return dto;
            })
            .toList();
    }

    @Transactional
    public SaveResponseDto toggleSave(Long postId, Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post não encontrado"));
        
        boolean alreadySaved = user.getSavedPosts().contains(post);

        if (alreadySaved) {
            user.getSavedPosts().remove(post);
        } else {
            user.getSavedPosts().add(post);
        }

        userRepository.save(user);

        return new SaveResponseDto(!alreadySaved);
    }

    public List<PostResponseDto> getByUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("Usuário não encontrado");
        }

        return repository.findByAuthorId(userId)    
            .stream()
            .map(PostMapper::toResponse)
            .toList();
    }

    public PostResponseDto create(PostCreateDto dto, MultipartFile image, User author){
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
