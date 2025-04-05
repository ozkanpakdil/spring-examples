package com.example.demo.rest;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.dto.RegisteredAccountIdDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Auth Api", description = "authentication")
public interface AuthApi {
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
    ResponseEntity<RegisteredAccountIdDto> register(@RequestBody RegisterDto registerDto);

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
    ResponseEntity<RegisteredAccountIdDto> authorize(@RequestBody LoginDto loginDto);
}
