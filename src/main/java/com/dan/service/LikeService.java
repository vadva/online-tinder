package com.dan.service;

import com.dan.Enteties.User;

import java.util.List;

public interface LikeService {
    public List<Integer> readLiked (int userId);
    public List<User> readLikedUsers (int userId);
    public void likeUser(int userId, int candidateId, boolean verdict);
}
