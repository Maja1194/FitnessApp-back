package com.example.onlinefitnessback.services.impl;

import com.example.onlinefitnessback.repositories.UserHasCategoryRepository;
import com.example.onlinefitnessback.services.UserHasCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserHasCategoryServiceImpl implements UserHasCategoryService {
    private final UserHasCategoryRepository userHasCategoryRepository;

    public UserHasCategoryServiceImpl(UserHasCategoryRepository userHasCategoryRepository) {
        this.userHasCategoryRepository = userHasCategoryRepository;
    }

    public List<Integer> getSelectedCategoryIdsForUser(Integer userId) {
        return userHasCategoryRepository.findByUserId(userId)
                .stream()
                .map(subscription -> subscription.getCategory().getId())
                .collect(Collectors.toList());
    }
}
