package com.tin.springcoursera.entity;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Document("chat-message")
public class ChatMessage {
    @Id
    private String id;
    private int roomId;
    private String sender;
    private String content;
}
