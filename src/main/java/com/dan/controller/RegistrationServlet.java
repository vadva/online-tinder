package com.dan.controller;

import com.dan.Enteties.User;
import com.dan.service.UserService;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegistrationServlet extends HttpServlet {
  private UserService userService;
  private TemplateEngine templateEngine;

  public RegistrationServlet(UserService userService, TemplateEngine templateEngine) {
    this.userService = userService;
    this.templateEngine = templateEngine;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HashMap<String, Object> params = new HashMap<>();
    params.put("submitMapping", "/create");
    templateEngine.render("registration.ftl", params, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String login = req.getParameter("login");
    String password = req.getParameter("password");
    String name = req.getParameter("name");

    User user = new User(null, login, password, name);
    userService.create(user);
    resp.sendRedirect("/login");
  }

}