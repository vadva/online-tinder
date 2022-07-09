package com.dan.service;


import com.dan.Enteties.User;
import com.dan.dao.LikesDao;

import java.util.List;

public class LikeServiceImpl implements LikeService {

    private LikesDao likesDao;

    public LikeServiceImpl(LikesDao likesDao) {
        this.likesDao = likesDao;
    }

    @Override
    public List<Integer> readLiked(int userId) {
        return likesDao.readLiked(userId);
    }

    @Override
    public List<User> readLikedUsers(int userId) {
        return likesDao.readLikedUsers(userId);
    }

    @Override
    public void likeUser(int userId, int candidateId, boolean verdict) {

    }
}
