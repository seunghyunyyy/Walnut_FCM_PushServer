package com.test.fcm_test.fcm_test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/push/v1")
public class MainController {
    //test : "fpDtTE-jQYeTIKG7e-b1WJ:APA91bEsgKOG84ILCU9orWih217FsxdictflLL-EcV72x2FRYXSx_t7C19Ri_iLgVBiLoyfgGu04dUjiKrYiM3tj_NA1XgIPhqCvQPsXVN-D6R7SFRmcZdP-AjxIEWZOrPqq_WF8rmyO";

    private final FirebaseCloudMessageService firebaseCloudMessageService;
    private final UserRepository userRepository;
    private final MessagesRepository messagesRepository;

    @PutMapping("/tokens")
    public UserEntity putTokens(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO.getId() + "\n" + userDTO.getEmail() + "\n" + userDTO.getToken());
        return userRepository.save(new UserEntity(userDTO.getId(), userDTO.getEmail(), userDTO.getToken()));
    }
    //email 기준으로 put 구현 다시하기


    @PostMapping("/messages")
    public ResponseEntity postMessage(@RequestBody MessagesDTO messagesDTO) throws IOException {
        postMessageEntity(messagesDTO);
        firebaseCloudMessageService.sendMessageTo(
                messagesDTO.getToken(),
                messagesDTO.getTitle(),
                messagesDTO.getBody());
        return ResponseEntity.ok().build();
    }
    public void postMessageEntity(@RequestBody MessagesDTO messagesDTO) throws IOException {
        System.out.println(messagesDTO.getToken() + "\n" + messagesDTO.getTitle() + "\n" + messagesDTO.getBody() + "\n"+ messagesDTO.getOpcode() + "\n"+ messagesDTO.getTokenId() + "\n"+ messagesDTO.getMsgId());
        messagesRepository.save(new MessagesEntity(messagesDTO.getId(), messagesDTO.getToken(), messagesDTO.getTitle(), messagesDTO.getBody(), messagesDTO.getOpcode(), messagesDTO.getTokenId(), messagesDTO.getMsgId()));
    }

/*
    @GetMapping("/push/v1/messages")
    public String getTokens(@RequestParam String email) {

    }
*//*
    @GetMapping("/push/v1/messages/{msg_id}")
    public String getTokens(@RequestParam String email) {

    }
*/
}