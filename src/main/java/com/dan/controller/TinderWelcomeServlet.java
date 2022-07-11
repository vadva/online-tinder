package com.dan.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import com.dan.service.CookieUtil;
import static com.dan.controller.LoginFilter.USER_PARAM_ID;

public class TinderWelcomeServlet extends HttpServlet {
  private TemplateEngine templateEngine;
  public TinderWelcomeServlet(TemplateEngine templateEngine) {
    this.templateEngine = templateEngine;
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HashMap<String, Object> data = new HashMap<>(1);

    CookieUtil.getCookieByName(request, USER_PARAM_ID)
        .ifPresent(c -> {
          data.put("isOnline", "online");
        });

    templateEngine.render("index.ftl", data, response);
  }

}