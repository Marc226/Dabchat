package com.example.LoginService.common;

import com.example.LoginService.model.Message;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IMessageService {
    ResponseEntity<Message> sendMessage (Message message);
    ResponseEntity<List<Message>> getPendingMessages(String id);
}
