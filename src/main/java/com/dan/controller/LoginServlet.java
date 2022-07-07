package com.dan.controller;

import com.dan.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {
  private UserService userService;
  private TemplateEngine templateEngine;

  public LoginServlet(UserService userService, TemplateEngine templateEngine) {
    this.userService = userService;
    this.templateEngine = templateEngine;
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    templateEngine.render("login.ftl", new HashMap<>(), response);
  }

}