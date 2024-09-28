package com.tin.springcoursera.service;

import com.tin.springcoursera.entity.ChatMessage;
import com.tin.springcoursera.repository.ChatMessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;

    public ChatMessage createChatMessage(ChatMessage chatMessage) {
        return chatMessageRepository.save(chatMessage);
    }

    public List<ChatMessage> getAllChatMessages() {
        return chatMessageRepository.findAll();
    }
}
