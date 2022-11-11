package com.test.fcm_test.fcm_test;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String token;

    @Builder
    public UserDTO(Long id, String email, String token) {
        this.id = id;
        this.email = email;
        this.token = token;
    }
    public UserEntity toEntity() {
        return UserEntity.builder()
                .id(id)
                .email(email)
                .token(token)
                .build();
    }
}

