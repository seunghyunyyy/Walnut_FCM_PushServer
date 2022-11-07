package com.test.fcm_test.fcm_test;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestDTO {
    private String title;
    private String targetToken;
    private String body;
}
