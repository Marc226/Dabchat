package com.example.LoginService.service;

import com.example.LoginService.model.LoginForm;
import com.example.LoginService.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/login")
public class ServiceRoutes {

    @Autowired
    private RegisterService regService;

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
    @RequestMapping("/deleteProfile")
    public ResponseEntity<Boolean> deleteUser(@Valid @RequestBody User users){
        return regService.removeUser(users);
    }


}
