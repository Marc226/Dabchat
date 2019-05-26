package com.example.LoginService.common;

import com.example.LoginService.model.Message;

import com.example.LoginService.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IMessageService {
    ResponseEntity<Message> sendMessage (Message message);
    ResponseEntity<List<Message>> getPendingMessages(String id);
    ResponseEntity<Message> getMessage (String id);

    boolean removeUserFromPending(User user, String mailId);
}
