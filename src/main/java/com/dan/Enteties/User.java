package com.dan.Enteties;

import java.util.*;

public class User {
  private Long id;
  private String name;
  private int age;
  private String profession;
  private String pictureSrc;
  private Date lastLogin;

  private String login;
  private String password;

  private HashMap<Long, Boolean> isLiked;
  private List<Long> likedUsersIdList;
  private List<Long> chatsIdList;


  public User(Long id, String name, int age, String profession,
              String pictureSrc, Date lastLogin, String login, String password) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.profession = profession;
    this.pictureSrc = pictureSrc;
    this.lastLogin = lastLogin;
    this.login = login;
    this.password = password;
    this.isLiked = new HashMap<>();
    this.isLiked.put(0L, false);
    this.likedUsersIdList = new ArrayList<>();
    this.chatsIdList = new ArrayList<>();
  }

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }
  public void setAge(int age) {
    this.age = age;
  }

  public String getProfession() {
    return profession;
  }
  public void setProfession(String profession) {
    this.profession = profession;
  }

  public String getPictureSrc() {
    return pictureSrc;
  }
  public void setPictureSrc(String pictureSrc) {
    this.pictureSrc = pictureSrc;
  }

  public Date getLastLogin() {
    return lastLogin;
  }
  public void setLastLogin(Date lastLogin) {
    this.lastLogin = lastLogin;
  }

  public String getLogin() {
    return login;
  }
  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }

  public HashMap<Long, Boolean> getLiked() {
    return isLiked;
  }
  public void setLiked(HashMap<Long, Boolean> liked) {
    this.isLiked = liked;
  }

  public List<Long> getLikedUsersIdList() {
    return likedUsersIdList;
  }
  public void setLikedUsersIdList(List<Long> likedUsersIdList) {
    this.likedUsersIdList = likedUsersIdList;
  }

  public List<Long> getChatsIdList() {
    return chatsIdList;
  }
  public void setChatsIdList(List<Long> chatsIdList) {
    this.chatsIdList = chatsIdList;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User)) return false;
    User user = (User) o;
    return id.equals(user.id) && login.equals(user.login) && password.equals(user.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, login, password);
  }

  @Override
  public String toString() {
    return "User{" +
        "pictureSrc='" + pictureSrc + '\'' +
        ", name='" + name + '\'' +
        ", age=" + age +
        ", profession='" + profession + '\'' +
        ", lastLogin=" + lastLogin +
        ", liked=" + isLiked +
        '}';
  }

}