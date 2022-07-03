package com.dan.dao;

import com.dan.Enteties.User;

import java.util.List;

public interface LikesDao {
    public List<User> readLikedUsers (int userId);
}
