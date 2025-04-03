package com.example.demo.services.impl;

import com.example.demo.model.Photo;
import com.example.demo.repository.PhotoRepository;
import com.example.demo.services.PhotoService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class PhotoServiceImpl implements PhotoService {
    private final PhotoRepository photoRepository;

    private final static Path PATH = Paths.get("src/main/resources/images");
    private final static String JPG = ".jpg";

    @Autowired
    public PhotoServiceImpl(PhotoRepository photoRepository) throws IOException {
        this.photoRepository = photoRepository;
        Files.createDirectories(PATH);
    }

    @Override
    public String upload(MultipartFile file) {
        final String path = RandomStringUtils.random(6, true, false) + JPG;
        save(path, file);
        photoRepository.save(
                Photo.builder()
                        .path(path)
                        .build()
        );
        return path;
    }

    @Override
    public byte[] download(String fileName) {
        final Path imagePath = PATH.resolve(fileName);

        if (Files.exists(imagePath)) {
            try {
                return Files.readAllBytes(imagePath);
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Couldn't download file", e);
            }
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No image with this file name");
    }

    @Override
    public void delete(String fileName) {
        final Path imagePath = PATH.resolve(fileName);
        try {
            Files.deleteIfExists(imagePath);
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Couldn't delete image: %s".formatted(fileName)
            );
        }
    }

    @Override
    public void checkIfExists(String fileName) {
        final Path imagePath = PATH.resolve(fileName);
        if (Files.notExists(imagePath)) {
            throw new IllegalStateException("File: %s doesn't exist".formatted(fileName));
        }
    }

    private void save(String fileName, MultipartFile file) {
        try {
            final Path filePath = PATH.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Couldn't save file", e);
        }
    }

}
