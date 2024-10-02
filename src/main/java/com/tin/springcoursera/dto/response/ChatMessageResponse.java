package com.tin.springcoursera.dto.response;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.tin.springcoursera.entity.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ChatMessageResponse {
    @JsonUnwrapped
    private ChatMessage chatMessage;
    private String senderName;
}
