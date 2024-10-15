package com.tin.springcoursera.controller;

import com.tin.springcoursera.dto.response.ChatMessageResponse;
import com.tin.springcoursera.entity.ChatMessage;
import com.tin.springcoursera.service.ChatMessageService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api")
public class ChatController {
    private final ChatMessageService chatMessageService;

    @PostMapping("chat-messages")
    @PreAuthorize("@auth.isMemberOfCourse(#chatMessage.roomId)")
    public ChatMessage addChatMessage(@RequestBody ChatMessage chatMessage) {
        return chatMessageService.createChatMessage(chatMessage);
    }

    @GetMapping("courses/{courseId}/chat-messages")
    @PreAuthorize("@auth.isMemberOfCourse(#courseId)")
    public List<ChatMessageResponse> getChatMessages(@PathVariable int courseId) {
        return chatMessageService.getChatMessagesByRoomId(courseId);
    }
}
