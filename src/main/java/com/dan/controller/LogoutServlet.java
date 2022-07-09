package com.dan.controller;

import com.dan.service.CookieUtil;
import com.dan.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.dan.controller.LoginFilter.USER_PARAM_ID;

public class LogoutServlet extends HttpServlet{

  private UserService userService;
  private TemplateEngine templateEngine;

  public LogoutServlet(UserService userService, TemplateEngine templateEngine) {
    this.userService = userService;
    this.templateEngine = templateEngine;
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    CookieUtil.getCookieByName(request, USER_PARAM_ID)
        .ifPresent(c -> {
          c.setMaxAge(0);
          c.setPath("/");
          response.addCookie(c);
        });

    response.sendRedirect("/");
  }

}