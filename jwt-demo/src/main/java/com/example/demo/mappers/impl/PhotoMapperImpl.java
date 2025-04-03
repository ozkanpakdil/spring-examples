package com.example.demo.mappers.impl;

import com.example.demo.dto.PhotoDto;
import com.example.demo.mappers.PhotoMapper;
import com.example.demo.model.Photo;
import org.springframework.stereotype.Service;

@Service
public class PhotoMapperImpl implements PhotoMapper {
    @Override
    public Photo toEntity(PhotoDto photoDto) {
        return Photo.builder()
                .path(photoDto.getPath())
                .build();
    }

    @Override
    public PhotoDto toDto(Photo photo) {
        return PhotoDto.builder()
                .path(photo.getPath())
                .build();
    }
}
