package com.example.demo.rest.impl;

import com.example.demo.dto.AdminChangeRequestDto;
import com.example.demo.dto.SubscriptionResponseDto;
import com.example.demo.rest.AdminApi;
import com.example.demo.services.AccountIdService;
import com.example.demo.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminApiImpl implements AdminApi {
    private final SubscriptionService subscriptionService;
    private final AccountIdService accountIdService;

    @Autowired
    public AdminApiImpl(SubscriptionService subscriptionService,
                        AccountIdService accountIdService) {
        this.subscriptionService = subscriptionService;
        this.accountIdService = accountIdService;
    }

    @Override
    public ResponseEntity<List<SubscriptionResponseDto>> getAllSubscriptions() {
        return ResponseEntity.ok(subscriptionService.getAll());
    }

    @Override
    public ResponseEntity<List<AdminChangeRequestDto>> getAllRequestedChanges() {
        return ResponseEntity.ok(accountIdService.getAllRequestedChanges());
    }

    @Override
    public ResponseEntity<Void> permitChangeRequest(Long id) {
        accountIdService.permitChangeRequest(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> denyChangeRequest(Long id) {
        accountIdService.denyChangeRequest(id);
        return ResponseEntity.noContent().build();
    }
}
