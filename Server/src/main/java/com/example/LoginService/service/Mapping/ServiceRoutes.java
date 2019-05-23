package com.example.LoginService.service.Mapping;

import com.example.LoginService.common.IUserService;
import com.example.LoginService.model.LoginForm;
import com.example.LoginService.model.User;
import com.example.LoginService.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/login")
public class ServiceRoutes {

    @Autowired
    private RegisterService regService;

    @Autowired
    private IUserService userService;

    @RequestMapping("/")
    public String index(){
        return "Invalid end-point";
    }

    @CrossOrigin
    @RequestMapping("/loginTest")
    public String loginTest(){
        return "login test for debug purposes only";
    }

    @CrossOrigin
    @RequestMapping("/register")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        return regService.registerUser(user);
    }


    @CrossOrigin
    @RequestMapping("/loginUser")
    public ResponseEntity<User> loginUser(@Valid @RequestBody LoginForm form){
        System.out.println("connected");
        return regService.login(form.getEmail(), form.getPassword());
    }

    @CrossOrigin
    @RequestMapping("/changeProfile")
    public ResponseEntity<User> changeUser(@Valid @RequestBody User users){
        return regService.changeProfile(users);
    }

    @CrossOrigin
    @RequestMapping("/addFriend")
    public ResponseEntity<Boolean> addFriend(@Valid @RequestBody User user, @RequestParam String friendEmail){
        return userService.addFriend(user, friendEmail);
    }
    @CrossOrigin
    @RequestMapping("/getFriend")
    public ResponseEntity<User> getFriend(@Valid @RequestBody User user, @RequestParam String friendEmail){
        return userService.getFriend(user, friendEmail);
    }
    @CrossOrigin
    @RequestMapping("/getAllFriends")
    public ResponseEntity<List<User>> getAllFriends(@Valid @RequestBody User user){
        ResponseEntity<List<User>> users = userService.getAllFriends(user);
        System.out.println(users.getBody().size());
        return users;
    }

    @CrossOrigin
    @RequestMapping("/deleteProfile")
    public ResponseEntity<Boolean> deleteUser(@Valid @RequestBody User users){
        return regService.removeUser(users);
    }


}
