package com.dan.dao;

import com.dan.Enteties.Message;
import com.dan.Enteties.User;

import java.util.List;

public interface MessageDao {
    public Message createMessage();
    public Message deleteMessage();
    public List<Message> getAllMessages();
    public List<Message> getAllMessagesFromUserId(User user);
    public List<Message> getAllMessagesToUserId(User user);
    public Message updateMessageById(Message message);
}