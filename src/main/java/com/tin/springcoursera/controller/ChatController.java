package com.tin.springcoursera.controller;

import com.tin.springcoursera.dto.response.ChatMessageResponse;
import com.tin.springcoursera.entity.ChatMessage;
import com.tin.springcoursera.service.ChatMessageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api")
public class ChatController {
    private final ChatMessageService chatMessageService;

    @PostMapping("chat-messages")
    public ChatMessage addChatMessage(@RequestBody ChatMessage chatMessage) {
        return chatMessageService.createChatMessage(chatMessage);
    }

    @GetMapping("chat-messages")
    public List<ChatMessage> getAllChatMessages() {
        return chatMessageService.getAllChatMessages();
    }

    @GetMapping("courses/{courseId}/chat-messages")
    public List<ChatMessageResponse> getChatMessages(@PathVariable int courseId) {
        return chatMessageService.getChatMessagesByRoomId(courseId);
    }
}
