package com.example.onlinefitnessback.services.impl;

import com.example.onlinefitnessback.models.dto.Activity;
import com.example.onlinefitnessback.models.entities.ActivityEntity;
import com.example.onlinefitnessback.repositories.ActivityRepository;
import com.example.onlinefitnessback.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public List<Activity> getActivitiesByUserId(Integer userId) {
        return activityRepository.findByUserId(userId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Activity saveActivity(Activity activity) {
        ActivityEntity entity = mapToEntity(activity);
        ActivityEntity savedEntity = activityRepository.save(entity);
        return mapToDto(savedEntity);
    }

    private Activity mapToDto(ActivityEntity entity) {
        Activity dto = new Activity();
        dto.setId(entity.getId());
        dto.setExercise(entity.getExercise());
        dto.setDuration(entity.getDuration());
        dto.setResult(entity.getResult());
        dto.setIntensity(entity.getIntensity());
        dto.setWeight(entity.getWeight());
        dto.setDate(entity.getDate());
        dto.setUser(entity.getUser()); // Pretpostavlja se da DTO koristi UserEntity
        return dto;
    }

    private ActivityEntity mapToEntity(Activity dto) {
        ActivityEntity entity = new ActivityEntity();
        entity.setId(dto.getId());
        entity.setExercise(dto.getExercise());
        entity.setDuration(dto.getDuration());
        entity.setResult(dto.getResult());
        entity.setIntensity(dto.getIntensity());
        entity.setWeight(dto.getWeight());
        entity.setDate(dto.getDate());
        entity.setUser(dto.getUser()); // Pretpostavlja se da DTO koristi UserEntity
        return entity;
    }
}
