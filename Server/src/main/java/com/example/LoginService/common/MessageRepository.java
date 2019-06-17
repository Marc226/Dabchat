package com.example.LoginService.common;

import com.example.LoginService.model.Message;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {
    Message findMessageById(String id);
    List<Message> findAllByRecipientsIDContains(String userId);

}
