package com.example.LoginService.common;

import com.example.LoginService.model.Message;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, String> {
    Message findByid(ObjectId id);


}
