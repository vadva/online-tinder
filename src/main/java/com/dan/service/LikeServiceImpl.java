package com.dan.service;


import com.dan.dao.LikesDao;

import java.util.List;

public class LikeServiceImpl implements LikeService {

    private LikesDao likesDao;

    public LikeServiceImpl(LikesDao likesDao) {
        this.likesDao = likesDao;
    }

    @Override
    public List<Integer> readLikedUsers(int userId) {
        return likesDao.readLikedUsers(userId);
    }
}
