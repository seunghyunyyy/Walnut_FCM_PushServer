package com.test.fcm_test.fcm_test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MessagesDTO {
    private String token;
    private String title;
    private String body;
    private String opcode;
    private Long tokenId;
    private Long msgId;
    @Builder
    public MessagesDTO(String token, String title, String body, String opcode, Long tokenId, Long msgId) {
        this.token = token;
        this.title = title;
        this.body = body;
        this.opcode = opcode;
        this.tokenId = tokenId;
        this.msgId = msgId;
    }
    public MessagesEntity toEntity() {
        return MessagesEntity.builder()
                .token(token)
                .title(title)
                .body(body)
                .opcode(opcode)
                .tokenId(tokenId)
                .msgId(msgId)
                .build();
    }

}
