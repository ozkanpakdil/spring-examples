package com.example.demo.rest.impl;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.dto.RegisteredAccountIdDto;
import com.example.demo.rest.AuthApi;
import com.example.demo.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthApiImpl implements AuthApi {
    private final AuthService authService;

    @Autowired
    public AuthApiImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<RegisteredAccountIdDto> register(RegisterDto registerDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.register(registerDto));
    }

    @Override
    public ResponseEntity<RegisteredAccountIdDto> authorize(LoginDto loginDto) {
        return ResponseEntity.ok(authService.login(loginDto));
    }
}
