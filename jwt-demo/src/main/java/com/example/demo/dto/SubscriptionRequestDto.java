package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class SubscriptionRequestDto {
    private String subscriptionName;

    private Integer monthCount;

    private BankDetailsDto bankDetails;
}
