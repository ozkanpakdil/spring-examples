package com.example.demo.services;

import com.example.demo.dto.NewsRequestDto;
import com.example.demo.dto.NewsResponseDto;
import com.example.demo.model.Profile;

import java.util.List;

public interface NewsService {
    NewsResponseDto get(Long id);

    List<NewsResponseDto> getAll();

    NewsResponseDto publish(NewsRequestDto newsRequestDto, Profile profile);

    void delete(Long id, Profile profile);

    void edit(Long id, NewsRequestDto newsRequestDto, Profile profile);
}
