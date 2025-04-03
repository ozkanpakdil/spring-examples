package com.example.demo.services;

import com.example.demo.dto.AccountIdChangeRequestDto;
import com.example.demo.dto.AccountIdDto;
import com.example.demo.dto.AdminChangeRequestDto;
import com.example.demo.model.AccountId;

import java.util.List;

public interface AccountIdService {
    AccountId getAccountIdByToken(String token);

    AccountIdDto getAccountIdDtoByToken(String token);

    void requestChangeAccountId(String token, AccountIdChangeRequestDto accountIdChangeRequestDto);

    List<AdminChangeRequestDto> getAllRequestedChanges();

    void permitChangeRequest(Long id);

    void denyChangeRequest(Long id);

    void block(Long id, String token);
}
