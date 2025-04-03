package com.example.demo.rest.impl;

import com.example.demo.dto.TownDto;
import com.example.demo.rest.TownApi;
import com.example.demo.services.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TownApiImpl implements TownApi {
    private final TownService townService;

    @Autowired
    public TownApiImpl(TownService townService) {
        this.townService = townService;
    }

    @Override
    public ResponseEntity<List<TownDto>> getAll() {
        return ResponseEntity.ok(townService.getAll());
    }

    @Override
    public ResponseEntity<TownDto> create(TownDto townDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(townService.create(townDto));
    }
}
