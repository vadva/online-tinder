package com.tinder.dao;

import com.tinder.Enteties.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();

}
