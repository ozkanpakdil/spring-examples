package com.example.demo.services.impl;

import com.example.demo.dto.TownDto;
import com.example.demo.mappers.TownMapper;
import com.example.demo.repository.TownRepository;
import com.example.demo.services.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;
    private final TownMapper townMapper;

    @Autowired
    public TownServiceImpl(TownRepository townRepository,
                           TownMapper townMapper) {
        this.townRepository = townRepository;
        this.townMapper = townMapper;
    }

    @Override
    public List<TownDto> getAll() {
        return townRepository.findAll()
                .stream()
                .map(townMapper::toDto)
                .toList();
    }

    @Override
    public TownDto create(TownDto townDto) {
        return townMapper.toDto(
                townRepository.save(townMapper.toEntity(townDto))
        );
    }
}
