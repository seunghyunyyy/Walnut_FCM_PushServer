package com.test.fcm_test.fcm_test;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private Long no;
    private String email;
    private String token;

    @Builder
    public UserDTO(Long no, String email, String token) {
        this.no = no;
        this.email = email;
        this.token = token;
    }
    public UserEntity toEntity() {
        return UserEntity.builder()
                .no(no)
                .email(email)
                .token(token)
                .build();
    }
}

