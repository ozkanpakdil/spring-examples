package com.example.demo.services.impl;

import com.example.demo.dto.SubscriptionRequestDto;
import com.example.demo.dto.SubscriptionResponseDto;
import com.example.demo.dto.SubscriptionTypeDto;
import com.example.demo.dto.UnsubscriptionRequestDto;
import com.example.demo.mappers.SubscriptionMapper;
import com.example.demo.mappers.SubscriptionTypeMapper;
import com.example.demo.model.AccountId;
import com.example.demo.model.BankDetails;
import com.example.demo.model.Role;
import com.example.demo.model.Subscription;
import com.example.demo.model.SubscriptionType;
import com.example.demo.repository.SubscriptionRepository;
import com.example.demo.repository.SubscriptionTypeRepository;
import com.example.demo.services.BankDetailsService;
import com.example.demo.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionTypeRepository subscriptionTypeRepository;
    private final SubscriptionTypeMapper subscriptionTypeMapper;
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final BankDetailsService bankDetailsService;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionTypeRepository subscriptionTypeRepository,
                                   SubscriptionRepository subscriptionRepository,
                                   SubscriptionMapper subscriptionMapper,
                                   SubscriptionTypeMapper subscriptionTypeMapper,
                                   BankDetailsService bankDetailsService) {
        this.subscriptionTypeRepository = subscriptionTypeRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionMapper = subscriptionMapper;
        this.subscriptionTypeMapper = subscriptionTypeMapper;
        this.bankDetailsService = bankDetailsService;
    }

    @Override
    public SubscriptionResponseDto get(Long id, AccountId accountId) {
        final Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (subscription.getAccountId().equals(accountId) || Role.ADMIN.equals(accountId.getRole())) {
            return subscriptionMapper.toDto(subscription);
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    @Override
    public List<SubscriptionResponseDto> getAll() {
        return subscriptionRepository.findAll()
                .stream()
                .map(subscriptionMapper::toDto)
                .toList();
    }

    @Override
    public List<SubscriptionTypeDto> getAllAvailableSubscriptions() {
        return subscriptionTypeRepository.findAll()
                .stream()
                .map(subscriptionTypeMapper::toDto)
                .toList();
    }

    @Override
    public List<SubscriptionResponseDto> getAllByAccountId(AccountId accountId) {
        return subscriptionRepository.findAllByAccountId(accountId)
                .stream()
                .map(subscriptionMapper::toDto)
                .toList();
    }

    @Override
    public SubscriptionResponseDto subscribe(SubscriptionRequestDto subscriptionRequestDto, AccountId accountId) {
        final SubscriptionType subscriptionType = subscriptionTypeRepository.findByName(subscriptionRequestDto.getSubscriptionName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Subscription type not found"));
        if (subscriptionRepository.findByTypeAndAccountId(subscriptionType, accountId).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Such subscription already exists");
        }
        if (subscriptionType.isFree()) {
            return subscribeFree(subscriptionRequestDto, accountId);
        }
        return subscribePaid(subscriptionRequestDto, accountId);
    }

    @Override
    public void unsubscribe(UnsubscriptionRequestDto unsubscriptionRequestDto, AccountId accountId) {
        final Optional<Subscription> subscriptionOpt =getSubscripton(unsubscriptionRequestDto, accountId);
        if (subscriptionOpt.isEmpty()) {
            return;
        }
        final Subscription subscription = subscriptionOpt.get();
        final LocalDate nextPaymentDate = subscription.getNextPaymentDate();
        subscription.setNextPaymentDate(null);
        subscription.setEndDate(nextPaymentDate);
        subscriptionRepository.save(subscription);
    }

    @Override
    public void interrupt(UnsubscriptionRequestDto unsubscriptionRequestDto, AccountId accountId) {
        final Optional<Subscription> subscriptionOpt =getSubscripton(unsubscriptionRequestDto, accountId);
        if (subscriptionOpt.isEmpty()) {
            return;
        }
        final Subscription subscription = subscriptionOpt.get();
        subscription.setNextPaymentDate(null);
        subscription.setEndDate(LocalDate.now());
        subscriptionRepository.save(subscription);
    }

    private SubscriptionResponseDto subscribeFree(SubscriptionRequestDto subscriptionRequestDto, AccountId accountId) {
        final Subscription subscription = subscriptionMapper.toEntity(subscriptionRequestDto, true);
        subscription.setAccountId(accountId);
        return subscriptionMapper.toDto(subscriptionRepository.save(subscription));
    }

    private SubscriptionResponseDto subscribePaid(SubscriptionRequestDto subscriptionRequestDto, AccountId accountId) {
        final Subscription subscription = subscriptionMapper.toEntity(subscriptionRequestDto, false);
        subscription.setAccountId(accountId);
        final BankDetails bankDetails = bankDetailsService.saveBankDetails(subscriptionRequestDto.getBankDetails());
        subscription.setBankDetails(bankDetails);
        bankDetailsService.makePayment(accountId, bankDetails, subscription.getType().getPrice());
        return subscriptionMapper.toDto(subscriptionRepository.save(subscription));
    }

    private Optional<Subscription> getSubscripton(UnsubscriptionRequestDto unsubscriptionRequestDto, AccountId accountId) {
        final SubscriptionType subscriptionType = subscriptionTypeRepository.findByName(
                        unsubscriptionRequestDto.getSubscriptionName()
                )
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Subscription type not found")
                );
        return subscriptionRepository.findByTypeAndAccountId(
                subscriptionType,
                accountId
        );
    }
}
