package com.example.LoginService.service;

import com.example.LoginService.common.IUserService;
import com.example.LoginService.common.UserRepository;
import com.example.LoginService.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository repository;

    @Override
    public ResponseEntity<User> getUser(String id) {
        User user = repository.findById(id).get();
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<User>> getAllFriends(User user) {
        user = repository.findById(user.getId()).get();
        List<User> populatedFriendsList = new ArrayList<>();
        for(String mail : user.getFrientList()){
            User friend = repository.findByMail(mail);
            friend.createFriendList();
            friend.setPassWord("");
            populatedFriendsList.add(friend);

        }
        return new ResponseEntity<List<User>>(populatedFriendsList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateUser(User user) {
        repository.save(user);
        return new ResponseEntity<String>("",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> addFriend(User user, String email) {
        User usr = repository.findByMail(user.getMail());
        User newFriend = repository.findByMail(email);
        System.out.println("usr null: "+(usr==null));
        System.out.println("newFriend null: "+(newFriend==null));
        boolean success = false;
        if(usr!=null && newFriend!=null) {
            if(usr.getFrientList() == null) usr.createFriendList();

            if(!usr.getFrientList().contains(newFriend.getMail())) {
                newFriend.setPassWord("");
                usr.getFrientList().add(newFriend.getMail());
                repository.save(usr);
                success = true;
            }
        }

        return new ResponseEntity<Boolean>(success, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<User> getFriend(User user, String email) {
        User usr = repository.findByMail(user.getMail());

        for(String friendEmail : usr.getFrientList()) {
            if(email.equals(friendEmail)) {
                User friend = repository.findByMail(friendEmail);
                friend.setPassWord("");
                friend.createFriendList();
                return new ResponseEntity<User>(friend, HttpStatus.OK);
            }
        }
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<String> removeString(User user, String friend_id) {
        return null;
    }
}
