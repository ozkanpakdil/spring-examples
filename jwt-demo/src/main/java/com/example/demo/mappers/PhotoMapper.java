package com.example.demo.mappers;

import com.example.demo.dto.PhotoDto;
import com.example.demo.model.Photo;

public interface PhotoMapper {
    Photo toEntity(PhotoDto photoDto);

    PhotoDto toDto(Photo photo);
}
