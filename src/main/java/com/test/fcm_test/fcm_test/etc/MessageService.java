package com.test.fcm_test.fcm_test.etc;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
/*
@Component
@RequiredArgsConstructor
public class MessageService {
    private final ObjectMapper objectMapper;

    public String messageType(String token, String title, String body, String opcode, String tokenId, Long msgId) throws JsonParseException, JsonProcessingException, IOException {
        MessageDTO message = MessageDTO.builder()
                .token(token)
                .notification(MessageDTO.Notification.builder()
                                .title(title)
                                .body(body)
                                .build()
                        ).build())
                .data(MessageDTO.Data.builder()
                        .opcode(opcode)
                        .tokenId(tokenId)
                        .msgId(msgId)
                        .build()
                ).build();

        return objectMapper.writeValueAsString(message);
    }
}*/