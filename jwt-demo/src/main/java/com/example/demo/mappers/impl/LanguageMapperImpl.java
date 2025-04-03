package com.example.demo.mappers.impl;

import com.example.demo.dto.LanguageDto;
import com.example.demo.mappers.LanguageMapper;
import com.example.demo.model.Language;
import org.springframework.stereotype.Service;

@Service
public class LanguageMapperImpl implements LanguageMapper {
    @Override
    public Language toEntity(LanguageDto languageDto) {
        if (languageDto == null) {
            return null;
        }
        return Language.builder()
                .name(languageDto.getName())
                .build();
    }

    @Override
    public LanguageDto toDto(Language language) {
        if (language == null) {
            return null;
        }
        return LanguageDto.builder()
                .name(language.getName())
                .build();
    }
}
