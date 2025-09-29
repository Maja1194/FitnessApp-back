package com.example.onlinefitnessback.controllers;

import com.example.onlinefitnessback.models.dto.Message;
import com.example.onlinefitnessback.models.entities.MessageEntity;
import com.example.onlinefitnessback.models.requests.MessageRequest;
import com.example.onlinefitnessback.services.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/counselor")
    public ResponseEntity<Message> createMessage(@RequestBody MessageRequest messageRequest, Authentication auth) {
        Message createdMessage = messageService.sendToCounselor(messageRequest, auth);
        return ResponseEntity.ok(createdMessage);
    }

    @GetMapping("/{userId}/{receiverId}")
    public ResponseEntity<List<MessageEntity>> getMessagesBetweenUsers(
            @PathVariable Integer userId,
            @PathVariable Integer receiverId) {
        List<MessageEntity> messages = messageService.getMessagesBetweenUsers(userId, receiverId);
        return ResponseEntity.ok(messages);
    }

    @PostMapping
    public ResponseEntity<MessageEntity> sendMessage(@RequestBody MessageEntity message) {
        MessageEntity savedMessage = messageService.saveMessage(message);
        return ResponseEntity.ok(savedMessage);
    }
}
