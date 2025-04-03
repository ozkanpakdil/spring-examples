package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class SubscriptionTypeDto {
    private String name;

    private String description;

    private Integer price;
}
