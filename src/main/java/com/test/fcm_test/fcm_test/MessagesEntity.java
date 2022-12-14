package com.test.fcm_test.fcm_test;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@ToString
@Getter
@NoArgsConstructor
@Entity
public class MessagesEntity {
    private String token;
    private String title;
    private String body;
    private String opcode;
    private Long tokenId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long msgId;
    @Builder
    public MessagesEntity(String token, String title, String body, String opcode, Long tokenId, Long msgId) {
        this.token = token;
        this.title = title;
        this.body = body;
        this.opcode = opcode;
        this.tokenId = tokenId;
        this.msgId = msgId;
    }
}