package com.example.MessageService.common;

import com.example.MessageService.model.Message;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, String> {
    Message findById(ObjectId id);

}
