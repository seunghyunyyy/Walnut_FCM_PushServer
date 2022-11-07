package com.test.fcm_test.fcm_test;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final FirebaseCloudMessageService firebaseCloudMessageService;

    @PostMapping("/push/v1/messages")
    public ResponseEntity pushMessage(@RequestBody RequestDTO requestDTO) throws IOException {
        String test_token = "fpDtTE-jQYeTIKG7e-b1WJ:APA91bEsgKOG84ILCU9orWih217FsxdictflLL-EcV72x2FRYXSx_t7C19Ri_iLgVBiLoyfgGu04dUjiKrYiM3tj_NA1XgIPhqCvQPsXVN-D6R7SFRmcZdP-AjxIEWZOrPqq_WF8rmyO";
        System.out.println(requestDTO.getTargetToken() + " "
                +requestDTO.getTitle() + " " + requestDTO.getBody());

        firebaseCloudMessageService.sendMessageTo(
                requestDTO.getTargetToken(),
                requestDTO.getTitle(),
                requestDTO.getBody());
        return ResponseEntity.ok().build();
    }
}