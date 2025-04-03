package com.example.demo.services.impl;

import com.example.demo.dto.BankDetailsDto;
import com.example.demo.mappers.BankDetailsMapper;
import com.example.demo.model.AccountId;
import com.example.demo.model.BankDetails;
import com.example.demo.model.Payments;
import com.example.demo.repository.BankDetailsRepository;
import com.example.demo.repository.PaymentsRepository;
import com.example.demo.services.BankDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankDetailsServiceImpl implements BankDetailsService {
    private final BankDetailsRepository bankDetailsRepository;
    private final BankDetailsMapper bankDetailsMapper;
    private final PaymentsRepository paymentsRepository;

    @Autowired
    public BankDetailsServiceImpl(BankDetailsRepository bankDetailsRepository,
                                  BankDetailsMapper bankDetailsMapper,
                                  PaymentsRepository paymentsRepository) {
        this.bankDetailsRepository = bankDetailsRepository;
        this.bankDetailsMapper = bankDetailsMapper;
        this.paymentsRepository = paymentsRepository;
    }

    @Override
    public BankDetails saveBankDetails(BankDetailsDto bankDetailsDto) {
        return bankDetailsRepository.save(bankDetailsMapper.toEntity(bankDetailsDto));
    }

    @Override
    public void makePayment(AccountId accountId, BankDetails bankDetails, int amount) {
        paymentsRepository.save(
                Payments.builder()
                        .accountId(accountId)
                        .bankDetails(bankDetails)
                        .amount(amount)
                        .build()
        );
    }
}
