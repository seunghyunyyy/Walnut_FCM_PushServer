package com.test.fcm_test.fcm_test;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private String email;
    private String token;

    @Builder
    public UserDTO(String email, String token) {
        this.email = email;
        this.token = token;
    }
    public UserEntity toEntity() {
        return UserEntity.builder()
                .email(email)
                .token(token)
                .build();
    }
}

