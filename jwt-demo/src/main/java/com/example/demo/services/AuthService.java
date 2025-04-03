package com.example.demo.services;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.dto.RegisteredAccountIdDto;

public interface AuthService {
    RegisteredAccountIdDto register(RegisterDto registerDto);

    RegisteredAccountIdDto login(LoginDto loginDto);
}
