package com.example.onlinefitnessback.scheduled;

import com.example.onlinefitnessback.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    private final EmailService emailService;

    @Autowired
    public EmailScheduler(EmailService emailService) {
        this.emailService = emailService;
    }

    @Scheduled(cron = "0 0 10 * * ?")
    public void sendDailyCategoryEmails() {
        System.out.println("Running daily email scheduler...");
        emailService.sendCategoryEmails();
    }
}
