package com.example.demo.mappers.impl;

import com.example.demo.dto.SubscriptionTypeDto;
import com.example.demo.mappers.SubscriptionTypeMapper;
import com.example.demo.model.SubscriptionType;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionTypeMapperImpl implements SubscriptionTypeMapper {
    @Override
    public SubscriptionType toEntity(SubscriptionTypeDto subscriptionTypeDto) {
        return SubscriptionType.builder()
                .name(subscriptionTypeDto.getName())
                .description(subscriptionTypeDto.getDescription())
                .price(subscriptionTypeDto.getPrice())
                .build();
    }

    @Override
    public SubscriptionTypeDto toDto(SubscriptionType subscriptionType) {
        return SubscriptionTypeDto.builder()
                .name(subscriptionType.getName())
                .description(subscriptionType.getDescription())
                .price(subscriptionType.getPrice())
                .build();
    }
}
