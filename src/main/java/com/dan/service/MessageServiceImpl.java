package com.dan.service;

import com.dan.Enteties.Message;
import com.dan.Enteties.User;
import com.dan.dao.MessageDao;

import java.util.List;

public class MessageServiceImpl implements MessageService {

    MessageDao messageDao;

    public MessageServiceImpl(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Override
    public boolean createMessage(Message message) {
        return messageDao.createMessage(message);
    }

    @Override
    public boolean deleteMessage() {
        return false;
    }

    @Override
    public List<Message> getAllMessages() {
        return messageDao.getAllMessages();
    }

    @Override
    public List<Message> getAllMessagesFromUserId(User user) {
        return null;
    }

    @Override
    public List<Message> getAllMessagesToUserId(Integer fromUserId, Integer toUserId) {

        return messageDao.getAllMessagesToUserId(fromUserId, toUserId);
    }

    @Override
    public Message updateMessageById(Message message) {
        return null;
    }
}
