package com.example.LoginService.common;

import com.example.LoginService.model.User;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;

public interface IRegisterService {
    ResponseEntity<User> registerUser(User user);
    ResponseEntity<User> login(String email, String password);
    ResponseEntity<User> getProfile(ObjectId id);
    ResponseEntity<User> changeProfile(User user);
    ResponseEntity<Boolean> removeUser(User user);
    ResponseEntity<User> findByMail(String email);
}
