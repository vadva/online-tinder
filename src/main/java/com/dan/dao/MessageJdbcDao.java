package com.dan.dao;


import com.dan.Enteties.Message;
import com.dan.Enteties.User;
import org.postgresql.ds.PGPoolingDataSource;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
//import java.util.Date;

import java.util.List;

public class MessageJdbcDao implements MessageDao {
    private PGPoolingDataSource source;

    public MessageJdbcDao() {
        source = new SourceUtil().getSource();
    }

    @Override
    public boolean createMessage(Message message) {
        Connection connection = null;
        try {
            connection = source.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO tinder.messages(user_id_who_write, user_id_whom_write, message_text, message_data) VALUES (?, ?, ?, ?)");
            preparedStatement.setInt(1, message.getUser_id_who_write());
            preparedStatement.setInt(2, message.getUser_id_to_whom_write());
            preparedStatement.setString(3, message.getMessage_text());
            preparedStatement.setDate(4, Date.valueOf(message.getMessage_data()));
//            java.sql.Date sqlDate = new java.sql.Date(message.getLoginDate().getTime());

            int executionResult = preparedStatement.executeUpdate();

            return executionResult > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public boolean deleteMessage(Long Id) {
        return false;
    }

    @Override
    public List<Message> getAllMessages() {
        Connection connection = null;
        List<Message> allMessages = new ArrayList<>();
        try {
            connection = source.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tinder.messages");

            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long message_id = resultSet.getLong("message_id");
                Integer user_id_who_write = resultSet.getInt("user_id_who_write");
                Integer user_id_whom_write = resultSet.getInt("user_id_whom_write");
                String message_text = resultSet.getString("message_text");
                LocalDate message_data = LocalDate.parse(String.valueOf(resultSet.getDate("message_data")));

                Message message = new Message(message_id, user_id_who_write, user_id_whom_write, message_text, message_data);

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
    public List<Message> getAllMessagesToUserId(Integer fromUserId, Integer toUserId) {
        Connection connection = null;

        List<Message> allMessages = new ArrayList<>();
        try {
            connection = source.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM tinder.messages m \n" +
//                    "INNER JOIN tinder.users u \n" +
//                    "ON m.user_id_who_write = u.user_id \n" +
                    "where user_id_who_write in (?,?)\n" +
                    "and user_id_whom_write in (?,?)\n" +
                    "ORDER BY message_data");
            preparedStatement.setInt(1,  fromUserId);
            preparedStatement.setInt(2,  toUserId);
            preparedStatement.setInt(3,  fromUserId);
            preparedStatement.setInt(4,  toUserId);

//            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long message_id = resultSet.getLong("message_id");
                Integer user_id_who_write = resultSet.getInt("user_id_who_write");
                Integer user_id_whom_write = resultSet.getInt("user_id_whom_write");
                String message_text = resultSet.getString("message_text");
                LocalDate message_data = LocalDate.parse(String.valueOf(resultSet.getDate("message_data")));
                Time localTime = resultSet.getTime("message_time");

                Message message = new Message(message_id, user_id_who_write, user_id_whom_write, message_text, message_data,localTime);

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
    public Message updateMessageById(Long id) {
        return null;
    }

}
