package com.example.demo.mappers.impl;

import com.example.demo.dto.AccountIdChangeRequestDto;
import com.example.demo.dto.AccountIdDto;
import com.example.demo.dto.AdminChangeRequestDto;
import com.example.demo.mappers.AccountIdChangeRequestMapper;
import com.example.demo.mappers.AccountIdMapper;
import com.example.demo.model.AccountId;
import com.example.demo.model.AccountIdChangeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountIdChangeRequestMapperImpl implements AccountIdChangeRequestMapper {
    private final AccountIdMapper accountIdMapper;

    @Autowired
    public AccountIdChangeRequestMapperImpl(AccountIdMapper accountIdMapper) {
        this.accountIdMapper = accountIdMapper;
    }

    @Override
    public AccountIdChangeRequest toEntity(AccountIdChangeRequestDto accountIdChangeRequestDto, AccountId accountId) {
        return AccountIdChangeRequest.builder()
                .accountId(accountId)
                .requestedName(accountIdChangeRequestDto.getName())
                .requestedSurname(accountIdChangeRequestDto.getSurname())
                .build();
    }

    @Override
    public AdminChangeRequestDto toDto(AccountIdChangeRequest accountIdChangeRequest) {
        final AccountIdChangeRequestDto accountIdChangeRequestDto = AccountIdChangeRequestDto.builder()
                .name(accountIdChangeRequest.getRequestedName())
                .surname(accountIdChangeRequest.getRequestedSurname())
                .build();
        final AccountIdDto accountIdDto = accountIdMapper.toDto(accountIdChangeRequest.getAccountId());
        return AdminChangeRequestDto.builder()
                .id(accountIdChangeRequest.getId())
                .accountId(accountIdDto)
                .requestedChanges(accountIdChangeRequestDto)
                .build();
    }
}
