package com.example.LoginService.service;

import com.example.LoginService.common.IMessageService;
import com.example.LoginService.common.MessageRepository;
import com.example.LoginService.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MessageService implements IMessageService {

    @Autowired
    private MessageRepository repository;

    @Override
    public ResponseEntity<Message> sendMessage(Message message) {
        repository.save(message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
