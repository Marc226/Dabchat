package com.example.LoginService.service;

import com.example.LoginService.common.IMessageService;
import com.example.LoginService.common.MessageRepository;
import com.example.LoginService.model.Message;
import com.example.LoginService.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService implements IMessageService {

    @Autowired
    private MessageRepository repository;

    @Override
    public ResponseEntity<Message> sendMessage(Message message) {

        if(repository.findByid(message.getId()) == null) {
            repository.save(message);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Message>> getPendingMessages(String id) {
        List<Message> messages = repository.findAllByRecipientsIDContains(id);
        for(Message msg : messages) {
            msg.removeRecipient(id);

            if(msg.recipientCount() > 0) {
                repository.save(msg);
            } else {
                repository.delete(msg);
            }
        }


        return new ResponseEntity<List<Message>>(messages, HttpStatus.OK);
    }
}
