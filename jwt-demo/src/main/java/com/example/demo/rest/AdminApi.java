package com.example.demo.rest;

import com.example.demo.dto.AdminChangeRequestDto;
import com.example.demo.dto.SubscriptionResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Tag(name = "Admin Api", description = "admin")
public interface AdminApi {
    @GetMapping("/admin/subscriptions")
    ResponseEntity<List<SubscriptionResponseDto>> getAllSubscriptions();

    @GetMapping("/admin/requests")
    ResponseEntity<List<AdminChangeRequestDto>> getAllRequestedChanges();

    @PutMapping("/admin/requests/{id}/accept")
    ResponseEntity<Void> permitChangeRequest(@PathVariable("id") Long id);

    @PutMapping("/admin/requests/{id}/deny")
    ResponseEntity<Void> denyChangeRequest(@PathVariable("id")Long id);
}
