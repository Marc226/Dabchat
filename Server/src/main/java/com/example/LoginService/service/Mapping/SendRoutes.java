package com.example.LoginService.service.Mapping;

import com.example.LoginService.model.LoginForm;
import com.example.LoginService.model.Message;
import com.example.LoginService.model.User;
import com.example.LoginService.service.RegisterService;
import com.example.LoginService.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/send")
public class SendRoutes {
    @Autowired
    private RegisterService regService;

    @Autowired
    private MessageService messageService;

    @RequestMapping("/upload")
    public ResponseEntity<Message> uploadMessage(@Valid @RequestBody Message message){

        messageService.sendMessage(message);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping("/get_pending")
    public ResponseEntity<List<Message>> getMessagesForUser(@Valid @RequestBody String id) {

        ResponseEntity<List<Message>> messages = messageService.getPendingMessages(id.substring(1, id.length()-1));

        return messages;
    }

    @RequestMapping("/remove_from_pending")
    public ResponseEntity<String> removeUserFromPending(@Valid @RequestBody User user, @RequestParam String messageId) {
        boolean success;
        success = messageService.removeUserFromPending(user, messageId);

        if(success)
            return new ResponseEntity<>("User was removed successfully!", HttpStatus.OK);
        else
            return new ResponseEntity<>("The user was not found in the given message id list!", HttpStatus.NOT_FOUND);
    }

    @RequestMapping("/user_has_messages")
    public ResponseEntity<List<User>> userHasMessages(@Valid @RequestBody LoginForm form) {
        User user = regService.login(form.getEmail(), form.getPassword()).getBody();
        ResponseEntity<List<User>> messages = messageService.getFriendsWithPendingMessages(user.getId());

        return messages;
    }

    @RequestMapping("/test")
    public ResponseEntity<String> generateTestData(){
        Message message = new Message();
        message.setFromUser(new User(null, "ubv", "silverleaves13@gmail.com)"));
        message.addRecipient("5cb446b602500342781b4198");
        messageService.sendMessage(message);
        return null;
    }
}
