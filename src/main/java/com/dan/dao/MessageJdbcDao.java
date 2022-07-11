package com.dan.dao;


import com.dan.Enteties.Message;
import com.dan.Enteties.User;
import org.postgresql.ds.PGPoolingDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageJdbcDao implements MessageDao {
    private PGPoolingDataSource source;

    public MessageJdbcDao() {
        source = new SourceUtil().getSource();
    }

    @Override
    public Message createMessage() {
        return null;
    }

    @Override
    public Message deleteMessage() {
        return null;
    }

    @Override
    public List<Message> getAllMessages(){
        Connection connection = null;
            List <Message> allMessages = new ArrayList<>();
        try{
            connection= source.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tinder.messages");

            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                long message_id = resultSet.getLong("message_id");
                Integer user_id_who_write = resultSet.getInt("user_id_who_write");
                Integer user_id_whom_write = resultSet.getInt("user_id_whom_write");
                String message_text = resultSet.getString("message_text");
                Date message_data = resultSet.getDate("message_data");
                Message message = new Message(message_id,user_id_who_write,user_id_whom_write,message_text,message_data);

                allMessages.add(message);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return allMessages;
    }

    @Override
    public List<Message> getAllMessagesFromUserId(User user) {
        return null;
    }

    @Override
    public List<Message> getAllMessagesToUserId(User user) {
        return null;
    }

    @Override
    public Message updateMessageById(Message message) {
        return null;
    }

}
