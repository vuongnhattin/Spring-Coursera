package com.tin.springcoursera.repository;

import com.tin.springcoursera.entity.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    List<ChatMessage> findChatMessagesByRoomId(int roomId);
}
