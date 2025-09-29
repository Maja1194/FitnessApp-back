package com.example.onlinefitnessback.repositories;

import com.example.onlinefitnessback.models.entities.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {

    @Query("SELECT m FROM MessageEntity m WHERE (m.user.id = :currentUserId AND m.recipientId = :recipientId) OR (m.user.id = :recipientId AND m.recipientId = :currentUserId) ORDER BY m.id ASC")
    List<MessageEntity> findMessagesBetweenUsers(@Param("currentUserId") Integer currentUserId, @Param("recipientId") Integer recipientId);

}
