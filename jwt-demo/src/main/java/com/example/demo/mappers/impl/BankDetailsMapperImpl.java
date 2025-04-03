package com.example.demo.mappers.impl;

import com.example.demo.dto.BankDetailsDto;
import com.example.demo.mappers.BankDetailsMapper;
import com.example.demo.model.BankDetails;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class BankDetailsMapperImpl implements BankDetailsMapper {
    @Override
    public BankDetails toEntity(BankDetailsDto bankDetailsDto) {
        final String encodedCardNumber = Base64.getEncoder().encodeToString(bankDetailsDto.getCardNumber().getBytes());
        final String encodedCvv = Base64.getEncoder().encodeToString(bankDetailsDto.getCvv().getBytes());
        final String encodedValidityPeriod = Base64.getEncoder().encodeToString(bankDetailsDto.getValidityPeriod().getBytes());
        return BankDetails.builder()
                .bankAccountName(bankDetailsDto.getBankAccountName())
                .bankAccountSurname(bankDetailsDto.getBankAccountSurname())
                .cardNumber(encodedCardNumber)
                .cvv(encodedCvv)
                .validityPeriod(encodedValidityPeriod)
                .build();
    }

    @Override
    public BankDetailsDto toDto(BankDetails bankDetails) {
        final String decodedCardNumber = new String(Base64.getDecoder().decode(bankDetails.getCardNumber().getBytes()));
        final String decodedCvv = new String(Base64.getDecoder().decode(bankDetails.getCvv()));
        final String decodedValidityPeriod = new String(Base64.getDecoder().decode(bankDetails.getValidityPeriod()));

        return BankDetailsDto.builder()
                .bankAccountName(bankDetails.getBankAccountName())
                .bankAccountSurname(bankDetails.getBankAccountSurname())
                .cardNumber(decodedCardNumber)
                .cvv(decodedCvv)
                .validityPeriod(decodedValidityPeriod)
                .build();
    }
}
