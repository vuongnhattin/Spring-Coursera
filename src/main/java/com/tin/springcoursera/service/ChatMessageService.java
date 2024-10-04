package com.tin.springcoursera.service;

import com.tin.springcoursera.dto.response.ChatMessageResponse;
import com.tin.springcoursera.entity.ChatMessage;
import com.tin.springcoursera.entity.Users;
import com.tin.springcoursera.repository.ChatMessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final UserService userService;

    public ChatMessage createChatMessage(ChatMessage chatMessage) {
        return chatMessageRepository.save(chatMessage);
    }

    public List<ChatMessage> getAllChatMessages() {
        return chatMessageRepository.findAll();
    }

    public List<ChatMessageResponse> getChatMessagesByRoomId(int roomId) {
        List<ChatMessage> messages = chatMessageRepository.findChatMessagesByRoomId(roomId);
        List<ChatMessageResponse> responses = new ArrayList<>();
        for (ChatMessage message : messages) {
            Users sender = userService.findById(message.getSender());
            String senderName = sender.getFirstName() + " " + sender.getLastName();
            ChatMessageResponse build = ChatMessageResponse.builder()
                    .chatMessage(message)
                    .senderName(senderName)
                    .build();
            responses.add(build);
        }

        return responses;
    }
}
