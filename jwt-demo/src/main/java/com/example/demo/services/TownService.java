package com.example.demo.services;

import com.example.demo.dto.TownDto;

import java.util.List;

public interface TownService {
    List<TownDto> getAll();

    TownDto create(TownDto townDto);
}
