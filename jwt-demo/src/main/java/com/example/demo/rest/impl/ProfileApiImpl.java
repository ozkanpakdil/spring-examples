package com.example.demo.rest.impl;

import com.example.demo.dto.ProfileRequestDto;
import com.example.demo.dto.ProfileResponseDto;
import com.example.demo.model.AccountId;
import com.example.demo.rest.ProfileApi;
import com.example.demo.services.AccountIdService;
import com.example.demo.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProfileApiImpl implements ProfileApi {
    private final ProfileService profileService;
    private final AccountIdService accountIdService;

    @Autowired
    public ProfileApiImpl(ProfileService profileService,
                          AccountIdService accountIdService) {
        this.profileService = profileService;
        this.accountIdService = accountIdService;
    }

    @Override
    public ResponseEntity<ProfileResponseDto> getByLogin(String login) {
        return ResponseEntity.ok(profileService.getProfileByLogin(login));
    }

    @Override
    public ResponseEntity<List<ProfileResponseDto>> getAll() {
        return ResponseEntity.ok(profileService.getAllProfiles());
    }

    @Override
    public ResponseEntity<Void> edit(Long id, ProfileRequestDto profileRequestDto, String token) {
        final AccountId accountId = accountIdService.getAccountIdByToken(token);
        profileService.edit(id, profileRequestDto, accountId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @Override
    public ResponseEntity<Void> block(Long id, String token) {
        profileService.block(id, accountIdService.getAccountIdByToken(token));
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
