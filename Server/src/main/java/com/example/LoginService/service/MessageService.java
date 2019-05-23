package com.example.LoginService.service;

import com.example.LoginService.common.IMessageService;
import com.example.LoginService.common.MessageRepository;
import com.example.LoginService.model.Message;
import com.example.LoginService.model.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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

    @Override
    public ResponseEntity<List<Message>> getPendingMessages(String id) {
        List<Message> messages = repository.findAllByRecipientsIDContains(id);
        System.out.println("ID:     "+id);
        for(Message msg : messages) {
            msg.removeRecipient(id);

            if(msg.recipientCount() > 0) {
                repository.save(msg);
            } else {
                repository.delete(msg);
            }
        }


        return new ResponseEntity<>(messages, HttpStatus.OK);
    }


    public ResponseEntity<List<User>> getFriendsWithPendingMessages(String id) {
        List<Message> messages = repository.findAllByRecipientsIDContains(id);
        List<User> friends = new ArrayList();
        for(Message msg : messages) {
            friends.add(msg.getFromUser());
            msg.getFromUser().setPassWord("");
        }


        return new ResponseEntity<>(friends, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Message> getMessage(String id) {
        if(!id.equals(null)){
            Message receivedMessage = repository.findByid(id);
              return new ResponseEntity<>(receivedMessage, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
