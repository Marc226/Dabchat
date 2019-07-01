package com.example.LoginService.common;

import com.example.LoginService.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    User findByMail(String mail);
    List<User> findByfirstName(String firstName);
    User findBypassWord(String passWord);
    User findUserById(String id);

}
