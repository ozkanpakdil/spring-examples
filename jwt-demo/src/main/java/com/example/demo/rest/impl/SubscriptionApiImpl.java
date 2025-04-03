package com.example.demo.rest.impl;

import com.example.demo.dto.SubscriptionRequestDto;
import com.example.demo.dto.SubscriptionResponseDto;
import com.example.demo.dto.SubscriptionTypeDto;
import com.example.demo.dto.UnsubscriptionRequestDto;
import com.example.demo.model.AccountId;
import com.example.demo.rest.SubscriptionApi;
import com.example.demo.services.AccountIdService;
import com.example.demo.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SubscriptionApiImpl implements SubscriptionApi {
    private final SubscriptionService subscriptionService;
    private final AccountIdService accountIdService;

    @Autowired
    public SubscriptionApiImpl(SubscriptionService subscriptionService,
                               AccountIdService accountIdService) {
        this.subscriptionService = subscriptionService;
        this.accountIdService = accountIdService;
    }

    @Override
    public ResponseEntity<SubscriptionResponseDto> get(Long id, String token) {
        final AccountId accountId = accountIdService.getAccountIdByToken(token);
        return ResponseEntity.ok(subscriptionService.get(id, accountId));
    }

    @Override
    public ResponseEntity<List<SubscriptionResponseDto>> getAllAccountsSubscriptions(String token) {
        final AccountId accountId = accountIdService.getAccountIdByToken(token);
        return ResponseEntity.ok(subscriptionService.getAllByAccountId(accountId));
    }

    @Override
    public ResponseEntity<List<SubscriptionTypeDto>> getAllAvailableSubscriptions() {
        return ResponseEntity.ok(subscriptionService.getAllAvailableSubscriptions());
    }

    @Override
    public ResponseEntity<SubscriptionResponseDto> subscribe(SubscriptionRequestDto subscriptionRequestDto, String token) {
        final AccountId accountId = accountIdService.getAccountIdByToken(token);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(subscriptionService.subscribe(subscriptionRequestDto, accountId));
    }

    @Override
    public ResponseEntity<Void> unsubscribe(UnsubscriptionRequestDto unsubscriptionRequestDto, String token) {
        final AccountId accountId = accountIdService.getAccountIdByToken(token);
        subscriptionService.unsubscribe(unsubscriptionRequestDto, accountId);
        return ResponseEntity.noContent().build();
    }
}
