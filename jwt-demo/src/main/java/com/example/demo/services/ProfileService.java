package com.example.demo.services;

import com.example.demo.dto.ProfileRequestDto;
import com.example.demo.dto.ProfileResponseDto;
import com.example.demo.model.AccountId;

import java.util.List;

public interface ProfileService {
    ProfileResponseDto getProfileByLogin(String login);

    List<ProfileResponseDto> getAllProfiles();

    void edit(Long id, ProfileRequestDto profileRequestDto, AccountId accountId);

    void block(Long id, AccountId accountId);
}
