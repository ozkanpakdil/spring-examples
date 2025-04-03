package com.example.demo.rest;

import com.example.demo.dto.SubscriptionRequestDto;
import com.example.demo.dto.SubscriptionResponseDto;
import com.example.demo.dto.SubscriptionTypeDto;
import com.example.demo.dto.UnsubscriptionRequestDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@Tag(name = "Subscription Api", description = "Подписки")
public interface SubscriptionApi {
    @GetMapping("/subscriptions/{id}")
    ResponseEntity<SubscriptionResponseDto> get(@PathVariable("id") Long id,
                                                @RequestHeader("Authorization") String token);

    @GetMapping("/subscriptions")
    ResponseEntity<List<SubscriptionResponseDto>> getAllAccountsSubscriptions(@RequestHeader("Authorization") String token);

    @GetMapping("/subscriptions/available")
    ResponseEntity<List<SubscriptionTypeDto>> getAllAvailableSubscriptions();

    @PostMapping("/subscriptions/subscribe")
    ResponseEntity<SubscriptionResponseDto> subscribe(@RequestBody SubscriptionRequestDto subscriptionRequestDto,
                                                      @RequestHeader("Authorization") String token);

    @DeleteMapping("/subscriptions/unsubscribe")
    ResponseEntity<Void> unsubscribe(@RequestBody UnsubscriptionRequestDto unsubscriptionRequestDto,
                                     @RequestHeader("Authorization") String token);
}
