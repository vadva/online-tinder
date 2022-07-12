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
        source = new SourceUtil().getSource();
    }

    @Override
    public List<Integer> readLiked(int userId) {
        Connection connection = null;
        List<Integer> likesIdList = new ArrayList<>();

        try {
            connection = source.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tinder.likes WHERE who_like_id = ? AND is_liked = true");
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
                            "SELECT * FROM tinder.users WHERE users.user_id IN" +
                                    " (SELECT whom_like_id FROM tinder.likes WHERE who_like_id = ? AND is_liked = true)"
                    );

            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                boolean isOnline = resultSet.getBoolean("is_online");
                String onlineStatus;

                if(isOnline) {
                    onlineStatus = "Online now";
                } else {
                    onlineStatus = "Last seen "  + resultSet.getString("log_out_date") + " ago";
                }

                User user = new User(
                    resultSet.getLong("user_id"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    onlineStatus,
                    resultSet.getString("name"),
                    resultSet.getInt("age"),
                    resultSet.getString("about_self"),
                    resultSet.getString("picture_src")
                );
                likedUsers.add(user);
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

        System.out.println(likedUsers);

        return likedUsers;
    }

    @Override
    public void likeUser(int userId, int candidateId, boolean verdict) {

    }
}
