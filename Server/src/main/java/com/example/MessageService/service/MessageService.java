package com.example.MessageService.service;

import com.example.MessageService.common.IMessageService;
import com.example.MessageService.common.MessageRepository;
import com.example.MessageService.model.Message;
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
