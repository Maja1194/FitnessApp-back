package com.example.onlinefitnessback.services;

import java.util.List;

public interface SubscriptionService {
     void subscribeToCategories(Integer userId, List<Integer> categoryIds);
}
