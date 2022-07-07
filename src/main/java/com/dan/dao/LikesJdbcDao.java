package com.dan.dao;

import com.dan.Enteties.User;
import org.postgresql.ds.PGPoolingDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LikesJdbcDao implements LikesDao {

    private PGPoolingDataSource source;

    public LikesJdbcDao() {
        source = new PGPoolingDataSource();
        source.setServerName("ec2-54-159-22-90.compute-1.amazonaws.com");
        source.setDatabaseName("d72gjotub2dfrp");
        source.setUser("wmnxpqntdirjkb");
        source.setPassword("ce9b5659604e1a2691c26617919799d3759e183430c601440e2c2d96a99b4653");
        source.setMaxConnections(10);
    }

    @Override
    public List<Integer> readLiked(int userId) {
        Connection connection = null;
        List<Integer> likesIdList = new ArrayList<>();

        try {
            connection = source.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM likes WHERE who_like_id = ? AND is_liked = true");
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                likesIdList.add(resultSet.getInt("whom_like_id"));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        }

        return likesIdList;
    }

    @Override
    public List<User> readLikedUsers(int userId) {
        List<User> likedUsers = new ArrayList<>();

        Connection connection = null;

        try {
            connection = source.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "SELECT * FROM users WHERE id IN" +
                                    " (SELECT whom_like_id FROM likes WHERE who_like_id = ? AND is_liked = true)"
                    );

            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
//                User user = new User(
                        // need table at DB
//                );
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        }

        return likedUsers;
    }

    @Override
    public void likeUser(int userId, int candidateId, boolean verdict) {

    }
}
