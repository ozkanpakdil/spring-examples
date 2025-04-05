package com.example.demo.rest;

import com.example.demo.dto.LanguageDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Language Api", description = "language")
public interface LanguageApi {
    @GetMapping("/languages")
    ResponseEntity<List<LanguageDto>> getAll();

    @PostMapping("/languages")
    ResponseEntity<LanguageDto> create(@RequestBody LanguageDto languageDto);
}
