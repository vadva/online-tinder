package com.dan.service;

import com.dan.Enteties.Message;
import com.dan.Enteties.User;

import java.util.List;

public interface MessageService {
    public boolean createMessage(Message message);
    public boolean deleteMessage();
    public List<Message> getAllMessages();
    public List<Message> getAllMessagesFromUserId(User user);
    public List<Message> getAllMessagesToUserId(Integer fromUserId,Integer toUserId);
    public Message updateMessageById(Message message);
}
