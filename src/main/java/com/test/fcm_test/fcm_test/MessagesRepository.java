package com.test.fcm_test.fcm_test;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessagesRepository extends CrudRepository <MessagesEntity, Long> {
}
