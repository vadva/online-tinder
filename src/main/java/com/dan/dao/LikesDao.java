package com.dan.dao;

import com.dan.Enteties.User;

import java.util.List;

public interface LikesDao {
    public List<Integer> readLikedUsers (int userId);
}
