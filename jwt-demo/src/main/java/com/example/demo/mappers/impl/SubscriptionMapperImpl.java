package com.example.demo.mappers.impl;

import com.example.demo.dto.SubscriptionRequestDto;
import com.example.demo.dto.SubscriptionResponseDto;
import com.example.demo.mappers.SubscriptionMapper;
import com.example.demo.mappers.SubscriptionTypeMapper;
import com.example.demo.model.Subscription;
import com.example.demo.model.SubscriptionType;
import com.example.demo.repository.SubscriptionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
public class SubscriptionMapperImpl implements SubscriptionMapper {
    private final SubscriptionTypeRepository subscriptionTypeRepository;
    private final SubscriptionTypeMapper subscriptionTypeMapper;

    @Autowired
    public SubscriptionMapperImpl(SubscriptionTypeRepository subscriptionTypeRepository,
                                  SubscriptionTypeMapper subscriptionTypeMapper) {
        this.subscriptionTypeRepository = subscriptionTypeRepository;
        this.subscriptionTypeMapper = subscriptionTypeMapper;
    }

    @Override
    public Subscription toEntity(SubscriptionRequestDto subscriptionRequestDto, boolean isFree) {
        final SubscriptionType subscriptionType = subscriptionTypeRepository
                .findByName(subscriptionRequestDto.getSubscriptionName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Subscription type not found"));
        final LocalDate startDate = LocalDate.now();
        final int monthCount = subscriptionRequestDto.getMonthCount();
        final LocalDate endDate = startDate.plusMonths(monthCount);
        final LocalDate nextPaymentDate;
        if (isFree) {
            nextPaymentDate = null;
        } else {
            nextPaymentDate = startDate.plusMonths(1);
        }
        return Subscription.builder()
                .type(subscriptionType)
                .startDate(startDate)
                .endDate(endDate)
                .nextPaymentDate(nextPaymentDate)
                .build();
    }

    @Override
    public SubscriptionResponseDto toDto(Subscription subscription) {
        return SubscriptionResponseDto.builder()
                .id(subscription.getId())
                .login(subscription.getAccountId().getLogin())
                .subscriptionType(subscriptionTypeMapper.toDto(subscription.getType()))
                .startDate(subscription.getStartDate())
                .endDate(subscription.getEndDate())
                .nextPaymentDate(subscription.getNextPaymentDate())
                .build();
    }
}
