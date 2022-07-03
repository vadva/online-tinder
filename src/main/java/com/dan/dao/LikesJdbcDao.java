package com.dan.dao;

import com.dan.Enteties.User;
import org.postgresql.ds.PGPoolingDataSource;

import java.util.List;

public class LikesJdbcDao implements LikesDao{

    private PGPoolingDataSource source;

    public LikesJdbcDao () {
        source = new PGPoolingDataSource();
        source.setServerName("SERVER_NAME");
        source.setDatabaseName("DB_NAME");
        source.setUser("USER");
        source.setPassword("PASSWORD");
        source.setMaxConnections(10);
    }

    @Override
    public List<User> readLikedUsers(int userId) {
        return null;
    }
}
