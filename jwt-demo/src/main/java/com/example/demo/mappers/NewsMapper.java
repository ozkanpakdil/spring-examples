package com.example.demo.mappers;

import com.example.demo.dto.NewsRequestDto;
import com.example.demo.dto.NewsResponseDto;
import com.example.demo.model.News;

public interface NewsMapper {
    News toEntity(NewsRequestDto newsRequestDto);

    NewsResponseDto toDto(News news);
}
