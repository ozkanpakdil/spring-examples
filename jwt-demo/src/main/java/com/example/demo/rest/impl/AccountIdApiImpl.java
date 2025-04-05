package com.example.demo.rest.impl;

import com.example.demo.dto.AccountIdChangeRequestDto;
import com.example.demo.dto.AccountIdDto;
import com.example.demo.rest.AccountIdApi;
import com.example.demo.services.AccountIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountIdApiImpl implements AccountIdApi {
    private final AccountIdService accountIdService;

    @Autowired
    public AccountIdApiImpl(AccountIdService accountIdService) {
        this.accountIdService = accountIdService;
    }

    @Override
    public ResponseEntity<AccountIdDto> get(String token) {
        return ResponseEntity.ok(accountIdService.getAccountIdDtoByToken(token));
    }

    @Override
    public ResponseEntity<String> requestChange(AccountIdChangeRequestDto accountIdChangeRequestDto, String token) {
        accountIdService.requestChangeAccountId(token, accountIdChangeRequestDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Request to change account details sent");
    }

    @Override
    public ResponseEntity<Void> block(Long id, String token) {
        accountIdService.block(id, token);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
