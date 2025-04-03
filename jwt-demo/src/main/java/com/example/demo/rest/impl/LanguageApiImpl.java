package com.example.demo.rest.impl;

import com.example.demo.dto.LanguageDto;
import com.example.demo.rest.LanguageApi;
import com.example.demo.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LanguageApiImpl implements LanguageApi {
    private final LanguageService languageService;

    @Autowired
    public LanguageApiImpl(LanguageService languageService) {
        this.languageService = languageService;
    }

    @Override
    public ResponseEntity<List<LanguageDto>> getAll() {
        return ResponseEntity.ok(languageService.getAll());
    }

    @Override
    public ResponseEntity<LanguageDto> create(LanguageDto languageDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(languageService.create(languageDto));
    }
}
