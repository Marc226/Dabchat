package com.example.LoginService.common;

import com.example.LoginService.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {
    public ResponseEntity<User> getUser(String id);
    public ResponseEntity<List<User>> getFriendList(User user);
    public ResponseEntity<String> updateUser(User user);
    ResponseEntity<String> addFriend(User user, String friend_id);
    ResponseEntity<String> removeString(User user, String friend_id);

}
