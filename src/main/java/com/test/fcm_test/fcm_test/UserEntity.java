package com.test.fcm_test.fcm_test;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@NoArgsConstructor
@Entity
public class UserEntity {
    @Id
    private String email;
    private String token;



    @Builder
    public UserEntity(String email, String token) {
        this.email = email;
        this.token = token;
    }
}

