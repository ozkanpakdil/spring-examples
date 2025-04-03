package com.example.demo.mappers.impl;

import com.example.demo.dto.ProfileRequestDto;
import com.example.demo.dto.ProfileResponseDto;
import com.example.demo.mappers.ProfileMapper;
import com.example.demo.model.Language;
import com.example.demo.model.Profile;
import com.example.demo.model.Town;
import com.example.demo.repository.LanguageRepository;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.repository.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProfileMapperImpl implements ProfileMapper {
    private final ProfileRepository profileRepository;
    private final TownRepository townRepository;
    private final LanguageRepository languageRepository;

    @Autowired
    public ProfileMapperImpl(ProfileRepository profileRepository,
                             TownRepository townRepository,
                             LanguageRepository languageRepository) {
        this.profileRepository = profileRepository;
        this.townRepository = townRepository;
        this.languageRepository = languageRepository;
    }

    /**
     * Профиль создается совместно с регистрацеий AccountId
     * AccountId в DTO не берется, оно меняется по другому бизнес-процессу
     */
    @Override
    public Profile toEntity(ProfileRequestDto profileRequestDto) {
        return Profile.builder()
                .login(profileRequestDto.getLogin())
                .shortDescription(profileRequestDto.getShortDescription())
                .maritalStatus(profileRequestDto.getMaritalStatus())
                .knownLanguages(getKnownLanguages(profileRequestDto))
                .family(getFamily(profileRequestDto))
                .homeTown(getTown(profileRequestDto))
                .build();
    }

    @Override
    public ProfileResponseDto toDto(Profile profile) {
        final Set<String> familyLogins = profile.getFamily()
                .stream()
                .map(Profile::getLogin)
                .collect(Collectors.toSet());
        final Town town = profile.getHomeTown();
        return ProfileResponseDto.builder()
                .id(profile.getId())
                .login(profile.getLogin())
                .shortDescription(profile.getShortDescription())
                .maritalStatus(profile.getMaritalStatus())
                .knownLanguages(
                        profile.getKnownLanguages()
                                .stream()
                                .map(Language::getName)
                                .collect(Collectors.toSet())
                )
                .familyLogins(familyLogins)
                .homeTown(town != null ? town.getName() : null)
                .build();
    }

    private Set<Profile> getFamily(ProfileRequestDto profileRequestDto) {
        return profileRequestDto.getFamilyLogins()
                .stream()
                .map(
                        login -> profileRepository.findByLogin(login)
                                .orElseThrow(
                                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found")
                                )
                )
                .collect(Collectors.toSet());
    }

    private Set<Language> getKnownLanguages(ProfileRequestDto profileRequestDto) {
        return profileRequestDto.getKnownLanguages()
                .stream()
                .map(
                        language -> languageRepository.findByName(language)
                                .orElseThrow(
                                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Language not found")
                                )
                )
                .collect(Collectors.toSet());
    }

    private Town getTown(ProfileRequestDto profileRequestDto) {
        if (profileRequestDto.getHomeTown() != null) {
            townRepository.findByName(profileRequestDto.getHomeTown())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Town not found"));
        }
        return null;
    }
}
