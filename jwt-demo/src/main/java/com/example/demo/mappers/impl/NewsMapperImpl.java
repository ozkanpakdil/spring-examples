package com.example.demo.mappers.impl;

import com.example.demo.dto.NewsRequestDto;
import com.example.demo.dto.NewsResponseDto;
import com.example.demo.mappers.NewsMapper;
import com.example.demo.model.News;
import com.example.demo.model.Photo;
import com.example.demo.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class NewsMapperImpl implements NewsMapper {
    private final PhotoRepository photoRepository;

    @Autowired
    public NewsMapperImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public News toEntity(NewsRequestDto newsRequestDto) {
        final List<Photo> photos = newsRequestDto.getPhotoPaths()
                .stream()
                .map(photo -> photoRepository.findByPath(photo)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo not found")))
                .toList();

        return News.builder()
                .text(newsRequestDto.getText())
                .photos(photos)
                .build();
    }

    @Override
    public NewsResponseDto toDto(News news) {
        final List<String> photoPaths = news.getPhotos()
                .stream()
                .map(Photo::getPath)
                .toList();
        return NewsResponseDto.builder()
                .id(news.getId())
                .text(news.getText())
                .photoPaths(photoPaths)
                .build();
    }
}
