package com.example.demo.rest;

import com.example.demo.dto.AccountIdChangeRequestDto;
import com.example.demo.dto.AccountIdDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Tag(name = "AccountId Api", description = "Аккаунты")
public interface AccountIdApi {
    @GetMapping("/accounts")
    ResponseEntity<AccountIdDto> get(@RequestHeader("Authorization") String token);

    @PutMapping("/accounts")
    ResponseEntity<String> requestChange(@RequestBody AccountIdChangeRequestDto accountIdChangeRequestDto,
                                         @RequestHeader("Authorization") String token);

    @DeleteMapping("/accounts/{id}")
    ResponseEntity<Void> block(@PathVariable("id") Long id,
                               @RequestHeader("Authorization") String token);
}
