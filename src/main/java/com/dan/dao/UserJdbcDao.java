package com.dan.dao;

import com.dan.Enteties.User;
import org.postgresql.ds.PGPoolingDataSource;

import java.util.List;

public class UserJdbcDao implements UserDao {

    private PGPoolingDataSource source;

    public UserJdbcDao() {
        source = new PGPoolingDataSource();
        source.setServerName("ec2-54-159-22-90.compute-1.amazonaws.com");
        source.setDatabaseName("d72gjotub2dfrp");
        source.setUser("wmnxpqntdirjkb");
        source.setPassword("ce9b5659604e1a2691c26617919799d3759e183430c601440e2c2d96a99b4653");
        source.setMaxConnections(10);
    }

    @Override
    public boolean create(User user) {
        return false;
    }

    @Override
    public User read(Long id) {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findByLoginPass(String login, String password) {
        return null;
    }

}