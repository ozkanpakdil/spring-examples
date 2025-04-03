package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class AdminChangeRequestDto {
    private Long id;

    private AccountIdDto accountId;

    private AccountIdChangeRequestDto requestedChanges;
}
