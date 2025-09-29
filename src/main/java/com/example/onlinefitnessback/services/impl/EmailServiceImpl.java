package com.example.onlinefitnessback.services.impl;

import com.example.onlinefitnessback.models.entities.UserHasCategoryEntity;
import com.example.onlinefitnessback.repositories.UserHasCategoryRepository;
import com.example.onlinefitnessback.services.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final UserHasCategoryRepository userHasCategoryRepository;

    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender, UserHasCategoryRepository userHasCategoryRepository) {
        this.mailSender = mailSender;
        this.userHasCategoryRepository = userHasCategoryRepository;
    }

    @Override
    @Transactional
    public void sendCategoryEmails() {
        List<UserHasCategoryEntity> subscriptions = userHasCategoryRepository.findAll();

        for (UserHasCategoryEntity subscription : subscriptions) {
            String userEmail = subscription.getUser().getEmail();
            String categoryName = subscription.getCategory().getCategoryName();

            try {
                var mimeMessage = mailSender.createMimeMessage();
                var helper = new MimeMessageHelper(mimeMessage, "utf-8");
                helper.setTo(userEmail);
                helper.setSubject("Daily Update for Category: " + categoryName);
                helper.setText("Hello! Here is your daily update for the category: " + categoryName, true);

                mailSender.send(mimeMessage);
                System.out.println("Email sent successfully to " + userEmail + " for category " + categoryName);

            } catch (MessagingException e) {
                System.err.println("Failed to send email to " + userEmail + ": " + e.getMessage());
            }
        }
    }

}
