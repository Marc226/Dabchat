package com.example.LoginService.service.Mapping;

import com.example.MessageService.model.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/send")
public class SendRoutes {
    @RequestMapping("/upload")
    public ResponseEntity<Message> uploadMessage(@Valid @RequestBody Message message){
        return null;
    }
}
