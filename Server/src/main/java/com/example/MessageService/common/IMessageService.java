package com.example.MessageService.common;

import com.example.MessageService.model.Message;

import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;

public interface IMessageService {
    ResponseEntity<Message> sendMessage (Message message);

}
