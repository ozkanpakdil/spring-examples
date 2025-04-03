package com.example.demo.mappers;

import com.example.demo.dto.AccountIdDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.model.AccountId;

public interface AccountIdMapper {
    AccountId toEntity(RegisterDto registerDto);

    AccountIdDto toDto(AccountId accountId);
}
