package com.example.demo.mappers.impl;

import com.example.demo.dto.TownDto;
import com.example.demo.mappers.TownMapper;
import com.example.demo.model.Town;
import org.springframework.stereotype.Service;

@Service
public class TownMapperImpl implements TownMapper {
    @Override
    public Town toEntity(TownDto townDto) {
        if (townDto == null) {
            return null;
        }
        return Town.builder()
                .name(townDto.getName())
                .build();
    }

    @Override
    public TownDto toDto(Town town) {
        if (town == null) {
            return null;
        }
        return TownDto.builder()
                .name(town.getName())
                .build();
    }
}
