package com.test.fcm_test.fcm_test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByNo(Long no);
    UserEntity findByEmail(String email);
    UserEntity findByToken(String token);
}
