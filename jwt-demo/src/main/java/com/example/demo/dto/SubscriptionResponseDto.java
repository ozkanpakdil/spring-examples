package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
public class SubscriptionResponseDto {
    private Long id;

    private String login;

    private SubscriptionTypeDto subscriptionType;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate nextPaymentDate;
}
