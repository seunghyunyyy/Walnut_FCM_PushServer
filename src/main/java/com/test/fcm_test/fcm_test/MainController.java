package com.test.fcm_test.fcm_test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/push/v1")
public class MainController {
    //test : "fpDtTE-jQYeTIKG7e-b1WJ:APA91bEsgKOG84ILCU9orWih217FsxdictflLL-EcV72x2FRYXSx_t7C19Ri_iLgVBiLoyfgGu04dUjiKrYiM3tj_NA1XgIPhqCvQPsXVN-D6R7SFRmcZdP-AjxIEWZOrPqq_WF8rmyO";

    private final FirebaseCloudMessageService firebaseCloudMessageService;
    private final UserRepository userRepository;
    private final MessagesRepository messagesRepository;

    @PutMapping ("/tokens")
    public UserEntity putTokens(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO.getNo() + "\n" + userDTO.getEmail() + "\n" + userDTO.getToken() + "\n");
        return userRepository.save(new UserDTO(userDTO.getNo(), userDTO.getEmail(), userDTO.getToken()).toEntity());
    }
    @GetMapping("/tokens")
    public String getTokens(@RequestBody UserDTO userDTO) {
        return userRepository.findByEmail(userDTO.getEmail()).getToken();
    }
    @PostMapping("/messages")
    public MessagesEntity postMessage(@RequestBody MessagesDTO messagesDTO) throws IOException {
        firebaseCloudMessageService.sendMessageTo(
                messagesDTO.getToken(),
                messagesDTO.getTitle(),
                messagesDTO.getBody());
        return messagesRepository.save(new MessagesDTO(messagesDTO.getNo(), messagesDTO.getToken(), messagesDTO.getTitle(), messagesDTO.getBody(),
                messagesDTO.getOpcode(), messagesDTO.getTokenId(), messagesDTO.getMsgId()).toEntity());
    }

    @GetMapping("/messages")
    public List<MessagesEntity> getMessages(@RequestBody MessagesDTO messagesDTO) {
        return messagesRepository.findByTokenContaining(messagesDTO.getToken());
    }
/*
    @GetMapping("/push/v1/messages/{msg_id}")
    public String getTokens(@RequestParam String email) {

    }

*//*
    public void postMessageEntity(@RequestBody MessagesDTO messagesDTO) {
        System.out.println(messagesDTO.getToken() + "\n" + messagesDTO.getTitle() + "\n" + messagesDTO.getBody() + "\n"+ messagesDTO.getOpcode() + "\n"+ messagesDTO.getTokenId() + "\n"+ messagesDTO.getMsgId() + "\n");
        messagesRepository.save(new MessagesDTO(messagesDTO.getNo(), messagesDTO.getToken(), messagesDTO.getTitle(), messagesDTO.getBody(), messagesDTO.getOpcode(), messagesDTO.getTokenId(), messagesDTO.getMsgId()).toEntity());
    }*/
}