package com.test.fcm_test.fcm_test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.util.ArrayList;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/push/v1")
public class MainController {
    private final Json json;
    private final FirebaseCloudMessageService firebaseCloudMessageService;
    private final UserRepository userRepository;
    private final MessagesRepository messagesRepository;
    @PutMapping ("/tokens")
    public UserEntity putTokens(@RequestBody UserDTO userDTO) {
        return userRepository.save(new UserDTO(userDTO.getEmail(), userDTO.getToken()).toEntity());
    }
    @GetMapping("/tokens")
    public String getTokens(@RequestParam(name = "email") String email) {
        return userRepository.findByEmail(email).getToken();
    }
    /*-----------------------------------------수정 전 POST Messages 코드-----------------------------------------*/
    @PostMapping("/messages")
    public MessagesEntity postMessage(@RequestBody MessagesDTO messagesDTO) throws IOException {
        firebaseCloudMessageService.sendMessageTo(messagesDTO.getToken(), messagesDTO.getTitle(), messagesDTO.getBody());
        return messagesRepository.save(new MessagesDTO(messagesDTO.getToken(), messagesDTO.getTitle(), messagesDTO.getBody(),
                messagesDTO.getOpcode(), messagesDTO.getTokenId(), messagesDTO.getMsgId()).toEntity());
    }
   /* -----------------------------------------수정 전 POST Messages 코드-----------------------------------------*/

    //-----------------------------------------수정 후 POST Messages 코드-----------------------------------------
   /* @PostMapping("/messages")
    public MessageEntity postMessage(@RequestBody MessageDTO messageDTO) throws IOException {
        //-----------------------------------------FCM 전송-----------------------------------------//
        firebaseCloudMessageService.sendMessageTo(
                messageDTO.getToken(),
                messageDTO.getNotification().getTitle(),
                messageDTO.getNotification().getBody());
        //-----------------------------------------전송한 메시지 저장-----------------------------------------//
        NotificationEntity notification = new NotificationEntity();
        notification.setTitle(messageDTO.getNotification().getTitle());
        notification.setBody(messageDTO.getNotification().getBody());
        nr.save(notification);
        //-----------------------------------------메시지 데이터 저장-----------------------------------------//
        DataEntity data = new DataEntity();
        data.setOpcode(messageDTO.getData().getOpcode());
        data.setTokenId(messageDTO.getData().getTokenId());
        data.setMsgId(messageDTO.getData().getMsgId());
        dr.save(data);
        //-----------------------------------------메시지 전체 정보 저장-----------------------------------------//
        MessageEntity message = new MessageEntity();
        message.setToken(messageDTO.getToken());
        message.setNotification(notification);
        message.setData(data);
        mr.save(message);

        return message;


    }*/
    //-----------------------------------------수정 후 POST Messages 코드-----------------------------------------


    /*-----------------------------------------수정 전 GET Messages 코드-----------------------------------------
    @GetMapping("/messages")
    public List<MessagesEntity> getMessages(@RequestParam(name = "token") String token) {
        return messagesRepository.findByTokenContaining(token);
    }
    -----------------------------------------수정 전 GET Messages 코드-----------------------------------------*/


    //-----------------------------------------수정 후 GET Messages 코드-----------------------------------------

    /**
     * @param token String TokenID
     * @param start defaultValue = "0"
     * @param end defaultValue = "0"
     * @return JsonArray
     */
    @GetMapping("/messages")
    public JsonArray getMessages(@RequestParam(name = "token") String token,
                                 @RequestParam(name = "start", required = false, defaultValue = "0") Integer start,
                                 @RequestParam(name = "end", required = false, defaultValue = "0") Integer end) {

        ArrayList<MessagesDTO> messagesDTOS = new ArrayList<>();
        int startTmp = 0, endTmp = messagesRepository.findByTokenContaining(token).size();

        if (start > 0) startTmp = start - 1;
        if (end > 0) endTmp = end;
        if (start == 0 && end == 0) return json.stringToMessageJsonArray(new Gson().toJson(messagesRepository.findByTokenContaining(token)));

        for (int i = startTmp; i < endTmp; i++) {
            MessagesDTO messages = new MessagesDTO();
            messages.setToken(token);
            messages.setTitle(messagesRepository.findByTokenContaining(token).get(i).getTitle());
            messages.setBody(messagesRepository.findByTokenContaining(token).get(i).getBody());
            messages.setOpcode(messagesRepository.findByTokenContaining(token).get(i).getOpcode());
            messages.setMsgId(messagesRepository.findByTokenContaining(token).get(i).getMsgId());
            messages.setTokenId(messagesRepository.findByTokenContaining(token).get(i).getTokenId());
            messagesDTOS.add(messages);
        }

        return json.stringToMessageJsonArray(new Gson().toJson(messagesDTOS));
    }
    //-----------------------------------------수정 후 GET Messages 코드-----------------------------------------
    @GetMapping("/messages/{msgId}")
    public JsonObject getMessagesId(@PathVariable("msgId") Long msgId) {
        //return messagesRepository.findByMsgId(msgId);
        return json.stringToMessageJsonObject(new Gson().toJson(messagesRepository.findByMsgId(msgId)));
    }
}