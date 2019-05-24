package com.example.LoginService.service.Mapping;

import com.example.LoginService.model.LoginForm;
import com.example.LoginService.model.Message;
import com.example.LoginService.model.User;
import com.example.LoginService.service.RegisterService;
import com.example.LoginService.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
        System.out.println("image data: \n"+message.getImage());
        System.out.println("Recipients!:");
        for(String rec : message.getRecipientsID()) {
            System.out.println(rec);
        }
        messageService.sendMessage(message);

        return null;
    }

    @RequestMapping("/get_pending")
    public ResponseEntity<List<Message>> getMessagesForUser(@Valid @RequestBody String id) {

        ResponseEntity<List<Message>> messages = messageService.getPendingMessages(id.substring(1, id.length()-1));

        return messages;
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
