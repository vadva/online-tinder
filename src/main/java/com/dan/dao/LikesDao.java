package com.dan.dao;

import com.dan.Enteties.User;

import java.util.List;

public interface LikesDao {
    public List<Integer> readLiked (int userId);
    public List<User> readLikedUsers (int userId);
    public void likeUser(int userId, int candidateId, boolean verdict);
}
