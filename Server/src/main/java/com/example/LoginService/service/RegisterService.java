package com.example.LoginService.service;

import com.example.LoginService.common.IRegisterService;
import com.example.LoginService.common.UserRepository;
import com.example.LoginService.model.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RegisterService implements IRegisterService {
    @Autowired
    private UserRepository repository;

    @Override
    public ResponseEntity<User> registerUser(User user) {
        if(repository.findBypassWord(user.getPassWord()) == null || repository.findByMail(user.getMail()) == null) {
            user.setId(ObjectId.get().toString());
            repository.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(user, HttpStatus.CONFLICT);
    }


    @Override
    public ResponseEntity<User> changeProfile(User user) {
        User users = repository.findByMail(user.getMail());

        if(users.getPassWord().contentEquals(user.getPassWord())){
            repository.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }

        return new ResponseEntity<>(users, HttpStatus.NOT_FOUND);
    }


    @Override
    public ResponseEntity<Boolean> removeUser(User user) {
        return new ResponseEntity<>(false, HttpStatus.OK);
    }



    @Override
    public ResponseEntity<User> login(String email, String password) {
        User user = repository.findByMail(email);
        if(user.getPassWord().contentEquals(password)){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(new User(null, null, null), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<User> findByMail(String email) {
        User user = repository.findByMail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<User> getProfile(ObjectId id) {
        User user = repository.findById(id);

        user.setPassWord(null);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
