package com.example.demo.rest.impl;

import com.example.demo.dto.PhotoDto;
import com.example.demo.rest.PhotoApi;
import com.example.demo.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class PhotoApiImpl implements PhotoApi {
    private final PhotoService photoService;

    @Autowired
    public PhotoApiImpl(PhotoService photoService) {
        this.photoService = photoService;
    }

    @Override
    public ResponseEntity<Resource> get(String path) {
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new ByteArrayResource(photoService.download(path)));
    }

    @Override
    public ResponseEntity<PhotoDto> add(MultipartFile file) {
        final PhotoDto photoDto = PhotoDto.builder()
                .path(photoService.upload(file))
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(photoDto);
    }

}
