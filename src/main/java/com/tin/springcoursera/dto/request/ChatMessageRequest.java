package com.tin.springcoursera.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageRequest {
    private int roomId;
    private String content;
}
