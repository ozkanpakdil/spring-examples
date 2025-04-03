package com.example.demo.dto;

import com.example.demo.model.MaritalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
@AllArgsConstructor
public class ProfileResponseDto {
    private Long id;

    private String login;

    private String shortDescription;

    private String homeTown;

    private Set<String> knownLanguages;

    private MaritalStatus maritalStatus;

    private Set<String> familyLogins;
}