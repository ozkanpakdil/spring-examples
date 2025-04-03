package com.example.demo.mappers;

import com.example.demo.dto.TownDto;
import com.example.demo.model.Town;

public interface TownMapper {
    Town toEntity(TownDto townDto);

    TownDto toDto(Town town);
}
