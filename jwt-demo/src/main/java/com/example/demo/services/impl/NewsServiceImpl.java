package com.example.demo.services.impl;

import com.example.demo.dto.NewsRequestDto;
import com.example.demo.dto.NewsResponseDto;
import com.example.demo.mappers.NewsMapper;
import com.example.demo.model.News;
import com.example.demo.model.Photo;
import com.example.demo.model.Profile;
import com.example.demo.repository.NewsRepository;
import com.example.demo.repository.PhotoRepository;
import com.example.demo.services.NewsService;
import com.example.demo.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    private final PhotoService photoService;
    private final PhotoRepository photoRepository;
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository,
                           NewsMapper newsMapper,
                           PhotoRepository photoRepository,
                           PhotoService photoService) {
        this.newsRepository = newsRepository;
        this.photoRepository = photoRepository;
        this.newsMapper = newsMapper;
        this.photoService = photoService;
    }

    @Override
    public NewsResponseDto get(Long id) {
        return newsMapper.toDto(
                newsRepository.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "News not found"))
        );
    }

    @Override
    public List<NewsResponseDto> getAll() {
        return newsRepository.findAll()
                .stream()
                .map(newsMapper::toDto)
                .toList();
    }

    @Override
    public NewsResponseDto publish(NewsRequestDto newsRequestDto, Profile profile) {
        final News news = newsMapper.toEntity(newsRequestDto);
        news.setProfile(profile);
        newsRepository.save(news);
        updatePhotoInfo(news);
        return newsMapper.toDto(news);
    }

    @Override
    public void edit(Long id, NewsRequestDto newsRequestDto, Profile profile) {
        final News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "News not found"));
        if (!news.getProfile().equals(profile)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        final News updated = newsMapper.toEntity(newsRequestDto);
        updated.setProfile(profile);
        updated.setId(news.getId());
        newsRepository.save(updated);
        updatePhotoInfo(updated);
    }

    @Override
    public void delete(Long id, Profile profile) {
        final News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "News not found"));
        if (!news.getProfile().equals(profile)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        final List<Photo> photos = news.getPhotos();
        photoRepository.deleteAll(photos);
        for (Photo photo : photos) {
            photoService.delete(photo.getPath());
        }
        newsRepository.delete(news);
    }

    private void updatePhotoInfo(News news) {
        for (Photo photo : news.getPhotos()) {
            photo.setNews(news);
            photoRepository.save(photo);
        }
    }
}
