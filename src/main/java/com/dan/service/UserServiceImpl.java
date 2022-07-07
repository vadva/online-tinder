package com.dan.service;

import com.dan.Enteties.User;
import com.dan.dao.UserDao;
import java.util.List;

public class UserServiceImpl implements UserService{

  private UserDao userDao;

  public UserServiceImpl(UserDao userDao) {
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
  public List<User> findAll() {
    return userDao.findAll();
  }

  @Override
  public User findByLoginPass(String login, String password) {
    return userDao.findByLoginPass(login, password);
  }

}