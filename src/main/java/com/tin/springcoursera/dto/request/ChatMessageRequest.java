package com.tin.springcoursera.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessageRequest {
    private int roomId;
    private String content;
}
