package com.example.demo.mappers;

import com.example.demo.dto.ProfileRequestDto;
import com.example.demo.dto.ProfileResponseDto;
import com.example.demo.model.Profile;

public interface ProfileMapper {
    Profile toEntity(ProfileRequestDto profileRequestDto);

    ProfileResponseDto toDto(Profile profile);
}
