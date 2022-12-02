package com.test.fcm_test.fcm_test;

import com.google.api.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessagesRepository extends JpaRepository<MessagesEntity, Long> {
    MessagesEntity findByToken(String token);
    MessagesEntity findByTitle(String title);
    MessagesEntity findByBody(String body);
    MessagesEntity findByOpcode(String opcode);
    MessagesEntity findByTokenId(String tokenId);
    MessagesEntity findByMsgId(Long msgId);
    List<MessagesEntity> findByTokenContaining(String token);
    List<MessagesEntity> findByMsgIdContaining(String msgId);
}
