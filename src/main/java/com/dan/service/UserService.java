package com.dan.service;

import com.dan.Enteties.User;
import com.dan.dao.UserDao;
import java.util.List;

public class UserService implements UserServiceImpl{

  private UserDao userDao;

  public UserService(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public boolean create(User user) {
    return userDao.create(user);
  }

  @Override
  public User read(Long id) {
    return userDao.read(id);
  }

  @Override
  public void update(User user) {
    userDao.update(user);
  }

  @Override
  public boolean delete(Long id) {
    return userDao.delete(id);
  }

  @Override
  public List<User> findAll() {
    return userDao.findAll();
  }

  @Override
  public User findByLoginPass(String login, String password) {
    return userDao.findByLoginPass(login, password);
  }


  // _________________________________________________________
  @Override
  public boolean setIsLiked(Long id, boolean isLiked) {
    return false;
  }

  @Override
  public boolean addLikedToList(Long user_id) {
    return false;
  }

  @Override
  public boolean addChatToList(Long chat_id) {
    return false;
  }

}