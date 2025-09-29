package com.example.onlinefitnessback.services;


import com.example.onlinefitnessback.models.dto.Message;
import com.example.onlinefitnessback.models.entities.MessageEntity;
import com.example.onlinefitnessback.models.requests.MessageRequest;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface MessageService {
    Message sendToCounselor(MessageRequest request, Authentication auth);
    List<MessageEntity> getMessagesBetweenUsers(Integer userId, Integer receiverId);
    MessageEntity saveMessage(MessageEntity message);
}
