package com.example.demo.services.impl;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.dto.RegisteredAccountIdDto;
import com.example.demo.jwt.UserAuthProvider;
import com.example.demo.mappers.AccountIdMapper;
import com.example.demo.model.AccountId;
import com.example.demo.model.Profile;
import com.example.demo.repository.AccountIdRepository;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.CharBuffer;

@Service
public class AuthServiceImpl implements AuthService {
    private final AccountIdRepository accountIdRepository;
    private final ProfileRepository profileRepository;
    private final AccountIdMapper accountIdMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserAuthProvider userAuthProvider;

    @Autowired
    public AuthServiceImpl(AccountIdRepository accountIdRepository,
                           ProfileRepository profileRepository,
                           AccountIdMapper accountIdMapper,
                           PasswordEncoder passwordEncoder,
                           UserAuthProvider userAuthProvider) {
        this.accountIdRepository = accountIdRepository;
        this.profileRepository = profileRepository;
        this.accountIdMapper = accountIdMapper;
        this.passwordEncoder = passwordEncoder;
        this.userAuthProvider = userAuthProvider;
    }

    @Override
    public RegisteredAccountIdDto register(RegisterDto registerDto) {
        if (accountIdRepository.findByLogin(registerDto.getLogin()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Such user already exists");
        }
        final AccountId accountId = accountIdMapper.toEntity(registerDto);
        final Profile createdProfile = new Profile();
        createdProfile.setFrozen(false);
        createdProfile.setLogin(registerDto.getLogin());
        accountId.setProfile(createdProfile);
        accountId.setFrozen(false);
        profileRepository.save(createdProfile);
        accountIdRepository.save(accountId);
        return new RegisteredAccountIdDto(userAuthProvider.createToken(accountId.getLogin(), accountId.getRole()));
    }

    @Override
    public RegisteredAccountIdDto login(LoginDto loginDto) {
        final AccountId accountId = accountIdRepository.findByLogin(loginDto.getLogin())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

        if (passwordEncoder.matches(CharBuffer.wrap(loginDto.getPassword()), accountId.getPassword())) {
            checkFrozen(accountId);
            return new RegisteredAccountIdDto(userAuthProvider.createToken(accountId.getLogin(), accountId.getRole()));
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bad credentials");
    }

    private void checkFrozen(AccountId accountId) {
        if (accountId.isFrozen() || accountId.getProfile().isFrozen()) {
            final Profile profile = accountId.getProfile();
            profile.setFrozen(false);
            accountId.setFrozen(false);
            accountId.setProfile(profileRepository.save(profile));
            accountIdRepository.save(accountId);
        }
    }
}
