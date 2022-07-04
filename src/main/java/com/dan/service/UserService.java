package com.dan.service;

import com.dan.Enteties.User;
import java.util.List;

public interface UserService {
  boolean create(User user);

  User read(Long id);

  void update(User user);

  boolean delete(Long id);

  List<User> findAll();

  User findByLoginPass(String login, String password);


  boolean setIsLiked(Long id, boolean isLiked);

  boolean addLikedToList(Long user_id);

  boolean addChatToList(Long chat_id);

}