package com.example.demo.services;

import com.example.demo.dto.BankDetailsDto;
import com.example.demo.model.AccountId;
import com.example.demo.model.BankDetails;

public interface BankDetailsService {
    BankDetails saveBankDetails(BankDetailsDto bankDetailsDto);

    void makePayment(AccountId accountId, BankDetails bankDetails,  int amount);
}
