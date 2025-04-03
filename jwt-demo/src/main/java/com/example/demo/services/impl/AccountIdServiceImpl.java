package com.example.demo.services.impl;

import com.example.demo.dto.AccountIdChangeRequestDto;
import com.example.demo.dto.AccountIdDto;
import com.example.demo.dto.AdminChangeRequestDto;
import com.example.demo.jwt.UserAuthProvider;
import com.example.demo.mappers.AccountIdChangeRequestMapper;
import com.example.demo.mappers.AccountIdMapper;
import com.example.demo.model.AccountId;
import com.example.demo.model.AccountIdChangeRequest;
import com.example.demo.model.Role;
import com.example.demo.repository.AccountIdChangeRequestRepository;
import com.example.demo.repository.AccountIdRepository;
import com.example.demo.services.AccountIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class AccountIdServiceImpl implements AccountIdService {
    private final UserAuthProvider userAuthProvider;
    private final AccountIdRepository accountIdRepository;
    private final AccountIdMapper accountIdMapper;
    private final AccountIdChangeRequestMapper accountIdChangeRequestMapper;
    private final AccountIdChangeRequestRepository accountIdChangeRequestRepository;

    @Autowired
    public AccountIdServiceImpl(UserAuthProvider userAuthProvider,
                                AccountIdRepository accountIdRepository,
                                AccountIdMapper accountIdMapper,
                                AccountIdChangeRequestMapper accountIdChangeRequestMapper,
                                AccountIdChangeRequestRepository accountIdChangeRequestRepository) {
        this.userAuthProvider = userAuthProvider;
        this.accountIdRepository = accountIdRepository;
        this.accountIdMapper = accountIdMapper;
        this.accountIdChangeRequestMapper = accountIdChangeRequestMapper;
        this.accountIdChangeRequestRepository = accountIdChangeRequestRepository;
    }

    @Override
    public AccountId getAccountIdByToken(String token) {
        final String login = userAuthProvider.getLoginFromJwt(token.split(" ")[1]);
        return accountIdRepository.findByLogin(login)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
    }

    @Override
    public AccountIdDto getAccountIdDtoByToken(String token) {
        return accountIdMapper.toDto(getAccountIdByToken(token));
    }

    @Override
    public void requestChangeAccountId(String token, AccountIdChangeRequestDto accountIdChangeRequestDto) {
        final AccountId accountId = getAccountIdByToken(token);
        final AccountIdChangeRequest changeRequest = accountIdChangeRequestMapper.toEntity(
                accountIdChangeRequestDto, accountId
        );
        accountIdChangeRequestRepository.save(changeRequest);
    }

    @Override
    public List<AdminChangeRequestDto> getAllRequestedChanges() {
        return accountIdChangeRequestRepository.findAll()
                .stream().
                map(accountIdChangeRequestMapper::toDto)
                .toList();
    }

    @Override
    public void permitChangeRequest(Long id) {
        final AccountIdChangeRequest changeRequest = accountIdChangeRequestRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        final AccountId accountId = changeRequest.getAccountId();
        accountId.setName(changeRequest.getRequestedName());
        accountId.setSurname(changeRequest.getRequestedSurname());
        accountIdRepository.save(accountId);
        accountIdChangeRequestRepository.delete(changeRequest);
    }

    @Override
    public void denyChangeRequest(Long id) {
        final AccountIdChangeRequest changeRequest = accountIdChangeRequestRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        accountIdChangeRequestRepository.delete(changeRequest);
    }

    @Override
    public void block(Long id, String token) {
        final AccountId accountIdToBlock = accountIdRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        final AccountId accountId = getAccountIdByToken(token);
        if (accountId.equals(accountIdToBlock) || Role.ADMIN.equals(accountId.getRole())) {
            accountIdToBlock.setFrozen(true);
            accountIdRepository.save(accountId);
        }
    }
}
