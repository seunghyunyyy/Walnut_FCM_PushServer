package com.test.fcm_test.fcm_test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/messages")
    public MessagesEntity postMessage(@RequestBody MessagesDTO messagesDTO) throws IOException {
        firebaseCloudMessageService.sendMessageTo(messagesDTO.getToken(), messagesDTO.getTitle(), messagesDTO.getBody());
        return messagesRepository.save(new MessagesDTO(messagesDTO.getToken(), messagesDTO.getTitle(), messagesDTO.getBody(),
                messagesDTO.getOpcode(), messagesDTO.getTokenId(), messagesDTO.getMsgId()).toEntity());
    }
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
    @GetMapping("/messages/{msgId}")
    public JsonObject getMessagesId(@PathVariable("msgId") Long msgId) {
        return json.stringToMessageJsonObject(new Gson().toJson(messagesRepository.findByMsgId(msgId)));
    }
}