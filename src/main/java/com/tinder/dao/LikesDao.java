package com.tinder.dao;

import com.tinder.Enteties.User;

import java.util.List;

public interface LikesDao {
    public List<User> readLikedUsers (int userId);
}
