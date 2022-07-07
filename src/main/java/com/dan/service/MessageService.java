package com.dan.service;

import com.dan.Enteties.Message;
import com.dan.Enteties.User;

import java.util.List;

public interface MessageService {
    public Message createMessage();
    public Message deleteMessage();
    public List<Message> getAllMessages();
    public List<Message> getAllMessagesFromUserId(User user);
    public List<Message> getAllMessagesToUserId(User user);
    public Message updateMessageById(Message message);
}