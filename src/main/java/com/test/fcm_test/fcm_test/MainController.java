package com.test.fcm_test.fcm_test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
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
    @GetMapping("/messages/{msg_id}")
    public MessagesEntity getMessagesId(@PathVariable("msg_id") Long msg_id) {
        return messagesRepository.findByMsgId(msg_id);//findByMsgIdContaining 사용 시 List<MessagesEntity> 반환
        //msg_id 값을 가진 Entity가 여러 개라면 List<MessagesEntity>로 변환
    }
}