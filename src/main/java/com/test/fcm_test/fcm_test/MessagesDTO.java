package com.test.fcm_test.fcm_test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MessagesDTO {
    private Long no;
    private String token;
    private String title;
    private String body;
    private String opcode;
    private Long tokenId;
    private Long msgId;

    @Builder
    public MessagesDTO(Long no, String token, String title, String body, String opcode, Long tokenId, Long msgId) {
        this.no = no;
        this.token = token;
        this.title = title;
        this.body = body;
        this.opcode = opcode;
        this.tokenId = tokenId;
        this.msgId = msgId;
    }

    public MessagesEntity toEntity() {
        return MessagesEntity.builder()
                .no(no)
                .token(token)
                .title(title)
                .body(body)
                .opcode(opcode)
                .tokenId(tokenId)
                .msgId(msgId)
                .build();
    }
}
