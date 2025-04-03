package com.example.demo.services;

import com.example.demo.dto.LanguageDto;

import java.util.List;

public interface LanguageService {
    List<LanguageDto> getAll();

    LanguageDto create(LanguageDto languageDto);
}
