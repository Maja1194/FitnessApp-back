package com.example.onlinefitnessback.services.impl;

import com.example.onlinefitnessback.models.dto.Message;
import com.example.onlinefitnessback.models.entities.CounselorEntity;
import com.example.onlinefitnessback.models.entities.MessageEntity;
import com.example.onlinefitnessback.models.entities.UserEntity;
import com.example.onlinefitnessback.models.requests.MessageRequest;
import com.example.onlinefitnessback.repositories.CounselorRepository;
import com.example.onlinefitnessback.repositories.MessageRepository;
import com.example.onlinefitnessback.repositories.UserRepository;
import com.example.onlinefitnessback.services.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CounselorRepository counselorRepository;

    public MessageServiceImpl(MessageRepository messageRepository, UserRepository userRepository, ModelMapper modelMapper, CounselorRepository counselorRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.counselorRepository = counselorRepository;
    }

    @Override
    public Message sendToCounselor(MessageRequest messageRequest, Authentication auth) {
        UserEntity user = userRepository.findById(messageRequest.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + messageRequest.getUserId() + " not found."));

        CounselorEntity counselor = counselorRepository.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No counselor found in the database."));

        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setText(messageRequest.getText());
        messageEntity.setMsgStatus(false);
        messageEntity.setUser(user);
        messageEntity.setRecipientId(counselor.getId());

        messageEntity = messageRepository.save(messageEntity);

        return modelMapper.map(messageEntity, Message.class);
    }

    public List<MessageEntity> getMessagesBetweenUsers(Integer userId, Integer receiverId) {
        return messageRepository.findMessagesBetweenUsers(userId, receiverId);
    }

    public MessageEntity saveMessage(MessageEntity message) {
        return messageRepository.save(message);
    }

}
