package com.example.demo.mappers;

import com.example.demo.dto.SubscriptionRequestDto;
import com.example.demo.dto.SubscriptionResponseDto;
import com.example.demo.model.Subscription;

public interface SubscriptionMapper {
    Subscription toEntity(SubscriptionRequestDto subscriptionRequestDto, boolean isFree);

    SubscriptionResponseDto toDto(Subscription subscription);
}
