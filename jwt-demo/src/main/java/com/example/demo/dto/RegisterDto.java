package com.example.demo.dto;

import com.example.demo.model.Sex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
public class RegisterDto {
    private String login;

    private String password;

    private String name;

    private String surname;

    private LocalDate birthDate;

    private Sex sex;
}
