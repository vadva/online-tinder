package com.dan.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class TinderWelcomeServlet extends HttpServlet {
  private TemplateEngine templateEngine;

  public TinderWelcomeServlet(TemplateEngine templateEngine) {
    this.templateEngine = templateEngine;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    templateEngine.render("/index.ftl", new HashMap<>(), resp);
  }

}