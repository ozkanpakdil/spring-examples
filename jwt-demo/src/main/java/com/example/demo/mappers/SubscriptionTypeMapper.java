package com.example.demo.mappers;

import com.example.demo.dto.SubscriptionTypeDto;
import com.example.demo.model.SubscriptionType;

public interface SubscriptionTypeMapper {
    SubscriptionType toEntity(SubscriptionTypeDto subscriptionTypeDto);

    SubscriptionTypeDto toDto(SubscriptionType subscriptionType);
}
