package com.example.LoginService.service;

import com.example.LoginService.common.IMessageService;
import com.example.LoginService.common.MessageRepository;
import com.example.LoginService.common.MessageSeenRepository;
import com.example.LoginService.model.Message;
import com.example.LoginService.model.MessageSeen;
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
    @Autowired
    private MessageSeenRepository messageSeenRepository;
    @Override
    public ResponseEntity<Message> sendMessage(Message message) {
        if(repository.findByid(message.getId()) == null) {
            message.setId(ObjectId.get().toString());
            repository.save(message);

            MessageSeen seenBy = new MessageSeen(message);
            seenBy.setRecipientsNotSeenId(message.getRecipientsID());
            messageSeenRepository.save(seenBy);

            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Message>> getPendingMessages(String id) {
        List<Message> messages = repository.findAllByRecipientsIDContains(id);
        System.out.println("Length:     "+ messages.size());
        for(Message msg : messages) {
            //msg.removeRecipient(id);
            //msg.setImage(null);
            if(msg.recipientCount() > 0) {
               // repository.save(msg);
            } else {
                repository.delete(msg);
            }
        }
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }


    public ResponseEntity<List<User>> getFriendsWithPendingMessages(String id) {
        List<MessageSeen> messageIsSeen = messageSeenRepository.findAllByRecipientsNotSeenIdContains(id);

        List<User> friends = new ArrayList();
        boolean alreadyRead = false;
        for(MessageSeen msg : messageIsSeen) {
            //Get user from original message.
            User fromUser = repository.findByid(msg.getMessageId()).getFromUser();
            msg.getRecipientsNotSeenId().remove(id);

            //Remove object if all users have seen the message.
            if(msg.getRecipientsNotSeenId().size()<1) messageSeenRepository.delete(msg);
            else messageSeenRepository.save(msg);

            //Removes password from the data sent back.
            fromUser.setPassWord("");
            friends.add(fromUser);
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

    @Override
    public boolean removeUserFromPending(User user, String mailId) {
        Message message = repository.findByid(mailId);
        if(message==null) return false;

        message.removeRecipient(user.getId());
        if(message.recipientCount()>0) {
            repository.save(message);
        } else {
            repository.delete(message);
        }

        return true;
    }


}
