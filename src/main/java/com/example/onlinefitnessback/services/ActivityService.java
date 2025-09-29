package com.example.onlinefitnessback.services;

import com.example.onlinefitnessback.models.dto.Activity;
import com.example.onlinefitnessback.models.entities.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityService {
    List<Activity> getActivitiesByUserId(Integer userId);
    Activity saveActivity(Activity activity);
}
