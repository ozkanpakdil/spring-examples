package com.example.demo.rest;

import com.example.demo.dto.NewsRequestDto;
import com.example.demo.dto.NewsResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@Tag(name = "News Api", description = "news")
public interface NewsApi {
    @GetMapping("/news/{id}")
    ResponseEntity<NewsResponseDto> get(@PathVariable("id") Long id);

    @GetMapping("/news")
    ResponseEntity<List<NewsResponseDto>> getAll();

    @PostMapping("/news")
    ResponseEntity<NewsResponseDto> publish(@RequestBody NewsRequestDto newsRequestDto,
                                            @RequestHeader("Authorization") String token);

    @DeleteMapping("/news/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") Long id,
                                @RequestHeader("Authorization") String token);

    @PutMapping("/news/{id}")
    ResponseEntity<Void> edit(@PathVariable("id") Long id,
                              @RequestBody NewsRequestDto newsRequestDto,
                              @RequestHeader("Authorization") String token);
}
