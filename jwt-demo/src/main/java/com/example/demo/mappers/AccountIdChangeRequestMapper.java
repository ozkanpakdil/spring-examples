package com.example.demo.mappers;

import com.example.demo.dto.AccountIdChangeRequestDto;
import com.example.demo.dto.AdminChangeRequestDto;
import com.example.demo.model.AccountId;
import com.example.demo.model.AccountIdChangeRequest;

public interface AccountIdChangeRequestMapper {
    AccountIdChangeRequest toEntity(AccountIdChangeRequestDto accountIdChangeRequestDto, AccountId accountId);

    AdminChangeRequestDto toDto(AccountIdChangeRequest accountIdChangeRequest);
}
