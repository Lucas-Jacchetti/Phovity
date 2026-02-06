package com.lucasjacc.dev.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {
    private static final String UPLOAD_DIR = "./uploads/";

    public static String save(MultipartFile image){
        Path uploadPath = Paths.get(UPLOAD_DIR);

        try {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String filename = UUID.randomUUID() + "_" + image.getOriginalFilename();
            Path target = uploadPath.resolve(filename);

            Files.copy(image.getInputStream(), target);

            return "/uploads/" + filename;

        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar imagem");
        }
        
    }
}
