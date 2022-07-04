package com.dan.dao;

import com.dan.Enteties.User;

import java.util.List;

public interface UserDao {
    boolean create(User user);

    User read(Long id);

    void update(User user);

    boolean delete(Long id);

    List<User> findAll();

    User findByLoginPass(String login, String password);

}
