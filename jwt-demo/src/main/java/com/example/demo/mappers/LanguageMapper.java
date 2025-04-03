package com.example.demo.mappers;

import com.example.demo.dto.LanguageDto;
import com.example.demo.model.Language;

public interface LanguageMapper {
    Language toEntity(LanguageDto languageDto);

    LanguageDto toDto(Language language);
}
