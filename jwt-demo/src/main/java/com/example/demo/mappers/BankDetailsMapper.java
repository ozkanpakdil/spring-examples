package com.example.demo.mappers;

import com.example.demo.dto.BankDetailsDto;
import com.example.demo.model.BankDetails;

public interface BankDetailsMapper {
    BankDetails toEntity(BankDetailsDto bankDetailsDto);

    BankDetailsDto toDto(BankDetails bankDetails);
}
