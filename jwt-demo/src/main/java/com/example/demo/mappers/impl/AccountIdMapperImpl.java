package com.example.demo.mappers.impl;

import com.example.demo.dto.AccountIdDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.mappers.AccountIdMapper;
import com.example.demo.model.AccountId;
import com.example.demo.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountIdMapperImpl implements AccountIdMapper {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountIdMapperImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AccountId toEntity(RegisterDto registerDto) {
        return AccountId.builder()
                .login(registerDto.getLogin())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .name(registerDto.getName())
                .surname(registerDto.getSurname())
                .sex(registerDto.getSex())
                .role(Role.USER)
                .birthDate(registerDto.getBirthDate())
                .build();
    }

    @Override
    public AccountIdDto toDto(AccountId accountId) {
        return AccountIdDto.builder()
                .login(accountId.getLogin())
                .name(accountId.getName())
                .surname(accountId.getSurname())
                .sex(accountId.getSex())
                .birthDate(accountId.getBirthDate())
                .build();
    }
}
