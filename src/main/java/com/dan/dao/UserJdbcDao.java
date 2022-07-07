package com.dan.dao;

import com.dan.Enteties.User;
import org.postgresql.ds.PGPoolingDataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDao implements UserDao{

    private PGPoolingDataSource source;

    public UserJdbcDao() {
        source = new PGPoolingDataSource();
        source.setServerName("ec2-54-159-22-90.compute-1.amazonaws.com");
        source.setDatabaseName("d72gjotub2dfrp");
        source.setUser("wmnxpqntdirjkb");
        source.setPassword("ce9b5659604e1a2691c26617919799d3759e183430c601440e2c2d96a99b4653");
        source.setMaxConnections(10);
    }

    @Override
    public boolean create(User user) {
        Connection connection = null;
        try {
            connection = source.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO tinder.users(name, login, password) VALUES (?, ?, ?)");
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());

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
    public User read(Long userId) {
        Connection connection = null;
        try {
            connection = source.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT user_id, login, password, is_online, name, age, about_self, picture_src, " +
                            "AGE(CURRENT_TIMESTAMP(0), log_out_date) as last_online FROM tinder.users WHERE user_id=?");
            preparedStatement.setString(1,  String.valueOf(userId));

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                long id = resultSet.getLong("user_id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                boolean isOnline = resultSet.getBoolean("is_online");
                String onlineStatus;
                if(isOnline) {
                    onlineStatus = "Online now";
                } else {
                    onlineStatus = "Last seen "  + resultSet.getString("last_online") + " ago";
                }
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String aboutSelf = resultSet.getString("about_self");
                String pictureSrc = resultSet.getString("picture_src");

                return new User(id, login, password, onlineStatus, name, age, aboutSelf, pictureSrc);
            }
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
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        try {
            connection = source.getConnection();
//      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users.users");
            PreparedStatement preparedStatement = connection.prepareStatement(
//          "SELECT id, name, login, password, lastLogin, pictureSrc, AGE() FROM users.users"
                    "SELECT user_id, login, password, is_online, name, age, about_self, picture_src, " +
                            "AGE(CURRENT_TIMESTAMP(0), log_out_date) as last_online FROM tinder.users"
            );

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                long id = resultSet.getLong("user_id");
                System.out.println("id = " + id);

                String login = resultSet.getString("login");
                System.out.println("login = " + login);

                String password = resultSet.getString("password");
                System.out.println("password = " + password);

                boolean isOnline = resultSet.getBoolean("is_online");
                System.out.println("isOnline = " + isOnline);

                String onlineStatus;
                if(isOnline) {
                    onlineStatus = "Online now";
                } else {
                    onlineStatus = "Last seen "  + resultSet.getString("last_online") + " ago";
                }
                System.out.println("onlineStatus = " + onlineStatus);

                String name = resultSet.getString("name");
                System.out.println("name = " + name);

                int age = resultSet.getInt("age");
                System.out.println("age = " + age);

                String aboutSelf = resultSet.getString("about_self");
                System.out.println("pictureSrc = " + aboutSelf);

                String pictureSrc = resultSet.getString("picture_src");
                System.out.println("pictureSrc = " + pictureSrc);

                users.add(new User(id, login, password, onlineStatus, name, age, aboutSelf, pictureSrc));
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
        System.out.println(users);
        return users;
    }

    @Override
    public User findByLoginPass(String loginUser, String passwordUser) {
        Connection connection = null;
        try {
            connection = source.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT user_id, login, password, is_online, name, age, about_self, picture_src, " +
                            "AGE(CURRENT_TIMESTAMP(0), log_out_date) as last_online FROM tinder.users WHERE login=? AND password=?");
            preparedStatement.setString(1, loginUser);
            preparedStatement.setString(2, passwordUser);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                long id = resultSet.getLong("user_id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                boolean isOnline = resultSet.getBoolean("is_online");
                String onlineStatus;
                if(isOnline) {
                    onlineStatus = "Online now";
                } else {
                    onlineStatus = "Last seen "  + resultSet.getString("last_online") + " ago";
                }
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String aboutSelf = resultSet.getString("about_self");
                String pictureSrc = resultSet.getString("picture_src");

                return new User(id, login, password, onlineStatus, name, age, aboutSelf, pictureSrc);
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
        return null;
    }

}