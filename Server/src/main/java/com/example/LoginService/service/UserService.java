package com.example.LoginService.service;

import com.example.LoginService.common.IUserService;
import com.example.LoginService.common.UserRepository;
import com.example.LoginService.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {

    @Autowired
    private UserRepository repository;

    @Override
    public ResponseEntity<User> getUser(String id) {
        User user = repository.findById(id).get();
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<User>> getFriendList(User user) {
        List<User> populatedFriendsList = new ArrayList<>();
        for(String id : user.getFrientList()){
            populatedFriendsList.add(repository.findById(id).get());
        }
        return new ResponseEntity<List<User>>(populatedFriendsList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateUser(User user) {
        repository.save(user);
        return new ResponseEntity<String>("",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> addFriend(User user, String friend_id) {
        
        return null;
    }

    @Override
    public ResponseEntity<String> removeString(User user, String friend_id) {
        return null;
    }
}
