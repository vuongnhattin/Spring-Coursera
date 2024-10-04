package com.tin.springcoursera.controller;

import com.tin.springcoursera.dto.request.ChatMessageRequest;
import com.tin.springcoursera.dto.response.ChatMessageResponse;
import com.tin.springcoursera.entity.ChatMessage;
import com.tin.springcoursera.entity.Member;
import com.tin.springcoursera.entity.Users;
import com.tin.springcoursera.service.ChatMessageService;
import com.tin.springcoursera.service.MemberService;
import com.tin.springcoursera.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class WebSocketController {
    private static final Logger log = LoggerFactory.getLogger(WebSocketController.class);
    private SimpMessagingTemplate messagingTemplate;
    private final MemberService memberService;
    private final ChatMessageService chatMessageService;
    private final UserService userService;

    @MessageMapping("/send")
    public void sendMessage(@Payload ChatMessageRequest request, Principal principal) {
        ChatMessage chatMessage = ChatMessage.builder()
                .sender(principal.getName())
                .roomId(request.getRoomId())
                .content(request.getContent())
                .build();

        chatMessageService.createChatMessage(chatMessage);

        Users sender = userService.findById(principal.getName());
        String senderName = sender.getFirstName() + " " + sender.getLastName();

        ChatMessageResponse response = ChatMessageResponse.builder()
                .chatMessage(chatMessage)
                .senderName(senderName)
                .build();

        List<Member> members = memberService.getMembersByCourseId(request.getRoomId());
        for (Member member : members) {
            messagingTemplate.convertAndSendToUser(member.getUserId(), "/queue/messages", response);
        }
    }
}
