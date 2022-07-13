package com.dan.dao;

import com.dan.Enteties.Message;
import com.dan.Enteties.User;

import java.util.List;

public interface MessageDao {
    public boolean createMessage(Message message);
    public boolean deleteMessage(Long Id);
    public List<Message> getAllMessages();
    public List<Message> getAllMessagesToUserId(Integer fromUserId,Integer toUserId);
    public Message updateMessageById(Long Id);
}
