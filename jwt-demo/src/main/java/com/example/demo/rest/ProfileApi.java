package com.example.demo.rest;

import com.example.demo.dto.ProfileRequestDto;
import com.example.demo.dto.ProfileResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@Tag(name = "Profile Api", description = "profile")
public interface ProfileApi {
    @GetMapping("/profiles")
    ResponseEntity<List<ProfileResponseDto>> getAll();

    @GetMapping("/profiles/{login}")
    ResponseEntity<ProfileResponseDto> getByLogin(@PathVariable("login") String login);

    @PutMapping("/profiles/{id}")
    ResponseEntity<Void> edit(@PathVariable("id") Long id,
                              @RequestBody ProfileRequestDto profileRequestDto,
                              @RequestHeader("Authorization") String token);

    @DeleteMapping("/profiles/{id}")
    ResponseEntity<Void> block(@PathVariable("id") Long id,
                               @RequestHeader("Authorization") String token);
}
