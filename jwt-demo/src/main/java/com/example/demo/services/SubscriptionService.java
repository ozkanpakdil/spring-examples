package com.example.demo.services;

import com.example.demo.dto.SubscriptionRequestDto;
import com.example.demo.dto.SubscriptionResponseDto;
import com.example.demo.dto.SubscriptionTypeDto;
import com.example.demo.dto.UnsubscriptionRequestDto;
import com.example.demo.model.AccountId;

import java.util.List;

public interface SubscriptionService {
    SubscriptionResponseDto get(Long id, AccountId accountId);

    List<SubscriptionResponseDto> getAll();

    List<SubscriptionTypeDto> getAllAvailableSubscriptions();

    List<SubscriptionResponseDto> getAllByAccountId(AccountId accountId);

    SubscriptionResponseDto subscribe(SubscriptionRequestDto subscriptionRequestDto, AccountId accountId);

    void unsubscribe(UnsubscriptionRequestDto unsubscriptionRequestDto, AccountId accountId);

    void interrupt(UnsubscriptionRequestDto unsubscriptionRequestDto, AccountId accountId);
}
