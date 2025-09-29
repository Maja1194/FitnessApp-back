package com.example.onlinefitnessback.controllers;

import com.example.onlinefitnessback.models.dto.Activity;
import com.example.onlinefitnessback.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping("/{userId}")
    public List<Activity> getActivitiesByUserId(@PathVariable Integer userId) {
        return activityService.getActivitiesByUserId(userId);
    }

    @PostMapping
    public Activity saveActivity(@RequestBody Activity activity) {
        return activityService.saveActivity(activity);
    }
}
