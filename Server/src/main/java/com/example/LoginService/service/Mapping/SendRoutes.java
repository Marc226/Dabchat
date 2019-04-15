package com.example.LoginService.service.Mapping;

import com.example.LoginService.model.Message;
import com.example.LoginService.service.RegisterService;
import com.example.LoginService.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/send")
public class SendRoutes {
    @Autowired
    private RegisterService regService;

    @Autowired
    private MessageService messageService;

    @RequestMapping("/upload")
    public ResponseEntity<Message> uploadMessage(@Valid @RequestBody Message message){
        return null;
    }

    @RequestMapping("/get_pending")
    public ResponseEntity<List<Message>> getMessagesForUser(@Valid @RequestBody String email) {
        User user = regService.findByMail(email).getBody();
        Message test = new Message().setFromUser(user);


        return new ResponseEntity<List<Message>>(test, HttpStatus.OK);
    }
}
