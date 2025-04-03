package com.example.demo.rest.impl;

import com.example.demo.dto.NewsRequestDto;
import com.example.demo.dto.NewsResponseDto;
import com.example.demo.model.Profile;
import com.example.demo.rest.NewsApi;
import com.example.demo.services.AccountIdService;
import com.example.demo.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NewsApiImpl implements NewsApi {
    private final NewsService newsService;
    private final AccountIdService accountIdService;

    @Autowired
    public NewsApiImpl(NewsService newsService, AccountIdService accountIdService) {
        this.newsService = newsService;
        this.accountIdService = accountIdService;
    }

    @Override
    public ResponseEntity<NewsResponseDto> get(Long id) {
        return ResponseEntity.ok(newsService.get(id));
    }

    @Override
    public ResponseEntity<List<NewsResponseDto>> getAll() {
        return ResponseEntity.ok(newsService.getAll());
    }

    @Override
    public ResponseEntity<NewsResponseDto> publish(NewsRequestDto newsRequestDto, String token) {
        final Profile profile = accountIdService.getAccountIdByToken(token).getProfile();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newsService.publish(newsRequestDto, profile));
    }

    @Override
    public ResponseEntity<Void> edit(Long id, NewsRequestDto newsRequestDto, String token) {
        final Profile profile = accountIdService.getAccountIdByToken(token).getProfile();
        newsService.edit(id, newsRequestDto, profile);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @Override
    public ResponseEntity<Void> delete(Long id, String token) {
        final Profile profile = accountIdService.getAccountIdByToken(token).getProfile();
        newsService.delete(id, profile);
        return ResponseEntity.noContent().build();
    }
}
