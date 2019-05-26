package com.example.LoginService.model;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class MessageSeen {

    @Id
    private String id;

    private String messageId;
    private List<String> recipientsNotSeenId;

    public MessageSeen() {

    }

    public MessageSeen(String messageId) {
        recipientsNotSeenId = new ArrayList();
        this.messageId = messageId;
    }

    public MessageSeen(Message message) {
        this(message.getId());
    }

    public String getMessageId() {
        return messageId;
    }

    public List<String> getRecipientsNotSeenId() {
        return recipientsNotSeenId;
    }

    public void setRecipientsNotSeenId(List<String> recipientsNotSeen) {
        this.recipientsNotSeenId = recipientsNotSeen;
    }

    public void addRecipientNotSeenId(User user) {
        this.recipientsNotSeenId.add(user.getId());
    }
    public void addRecipientNotSeenId(String userId) {
        this.recipientsNotSeenId.add(userId);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
