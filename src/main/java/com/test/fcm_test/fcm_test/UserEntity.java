package com.test.fcm_test.fcm_test;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@NoArgsConstructor
@Entity
public class UserEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;
    @Id
    private String email;

    private String token;



    @Builder
    public UserEntity(Long no, String email, String token) {
        this.no = no;
        this.email = email;
        this.token = token;
    }
}

