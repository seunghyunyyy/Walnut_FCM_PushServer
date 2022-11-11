package com.test.fcm_test.fcm_test;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@ToString
@Getter
@NoArgsConstructor
@Entity
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Id
    private String email;
    private String token;

    @Builder
    public UserEntity(Long id, String email, String token) {
        this.id = id;
        this.email = email;
        this.token = token;
    }
}

