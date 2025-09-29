package com.example.onlinefitnessback.services.impl;

import com.example.onlinefitnessback.models.entities.CategoryEntity;
import com.example.onlinefitnessback.models.entities.UserEntity;
import com.example.onlinefitnessback.models.entities.UserHasCategoryEntity;
import com.example.onlinefitnessback.repositories.CategoryRepository;
import com.example.onlinefitnessback.repositories.UserHasCategoryRepository;
import com.example.onlinefitnessback.repositories.UserRepository;
import com.example.onlinefitnessback.services.SubscriptionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final UserHasCategoryRepository userHasCategoryRepository;

    public SubscriptionServiceImpl(UserRepository userRepository, CategoryRepository categoryRepository, UserHasCategoryRepository userHasCategoryRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.userHasCategoryRepository = userHasCategoryRepository;
    }

    @Override
    public void subscribeToCategories(Integer userId, List<Integer> categoryIds) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Preuzmi sve trenutne pretplate korisnika
        List<UserHasCategoryEntity> existingSubscriptions = userHasCategoryRepository.findByUserId(userId);

        // Kreiraj skup trenutnih kategorija
        List<Integer> existingCategoryIds = existingSubscriptions.stream()
                .map(subscription -> subscription.getCategory().getId())
                .toList();

        // Identifikuj kategorije koje treba ukloniti
        List<UserHasCategoryEntity> subscriptionsToRemove = existingSubscriptions.stream()
                .filter(subscription -> !categoryIds.contains(subscription.getCategory().getId()))
                .toList();

        // Ukloni kategorije koje više nisu potrebne
        userHasCategoryRepository.deleteAll(subscriptionsToRemove);

        // Identifikuj nove kategorije koje treba dodati
        List<Integer> newCategoryIds = categoryIds.stream()
                .filter(categoryId -> !existingCategoryIds.contains(categoryId))
                .toList();

        // Dodaj nove kategorije
        List<UserHasCategoryEntity> subscriptionsToAdd = newCategoryIds.stream()
                .map(categoryId -> {
                    CategoryEntity category = categoryRepository.findById(categoryId)
                            .orElseThrow(() -> new RuntimeException("Category not found"));
                    UserHasCategoryEntity subscription = new UserHasCategoryEntity();
                    subscription.setUser(user);
                    subscription.setCategory(category);
                    return subscription;
                }).toList();

        userHasCategoryRepository.saveAll(subscriptionsToAdd);
    }

}

