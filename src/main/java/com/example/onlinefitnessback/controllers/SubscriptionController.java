package com.example.onlinefitnessback.controllers;

import com.example.onlinefitnessback.services.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping("/subscribe")
    public ResponseEntity<Void> subscribe(
            @RequestParam Integer userId,
            @RequestBody List<Integer> categoryIds) {
        subscriptionService.subscribeToCategories(userId, categoryIds);
        return ResponseEntity.ok().build();
    }
}
