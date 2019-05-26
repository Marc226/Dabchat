package com.example.LoginService.common;

import com.example.LoginService.model.Message;
import com.example.LoginService.model.MessageSeen;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageSeenRepository extends MongoRepository<MessageSeen, String> {
    MessageSeen findByid(String id);
    List<MessageSeen> findAllByRecipientsNotSeenIdContains(String userId);
}
