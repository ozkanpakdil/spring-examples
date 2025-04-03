package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class BankDetailsDto {
    private String bankAccountName;

    private String bankAccountSurname;

    private String cardNumber;

    private String validityPeriod;

    private String cvv;
}
