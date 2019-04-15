package com.example.LoginService.service;

import com.example.LoginService.common.IMessageService;
import com.example.LoginService.common.MessageRepository;
import com.example.LoginService.model.Message;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MessageService implements IMessageService {

    @Autowired
    private MessageRepository repository;

    @Override
    public ResponseEntity<Message> sendMessage(Message message) {

        if(repository.findByid(message.getId()) == null) {
            message.setId(ObjectId.get().toString());
            repository.save(message);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
