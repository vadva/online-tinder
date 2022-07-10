package com.dan.controller;

import com.dan.Enteties.User;
import com.dan.service.CookieUtil;
import com.dan.service.UserService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

public class LoginFilter implements Filter {
  public static final String USER_PARAM_ID = "id";
  private TemplateEngine templateEngine;
  private UserService userService;

  public LoginFilter(TemplateEngine templateEngine, UserService userService) {
    this.templateEngine = templateEngine;
    this.userService = userService;
  }

  public void init(FilterConfig config) throws ServletException {
  }

  public void destroy() {
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse resp,
                       FilterChain chain) throws ServletException, IOException {
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) resp;

    Set<String> allowedUrls = Set.of("/assets", "/", "/tinder", "/login", "/create");
    if (allowedUrls.contains(request.getServletPath())) {
      chain.doFilter(request, response);
    } else {

      Optional<Cookie> optionalCookie = CookieUtil.getCookieByName(request, USER_PARAM_ID); // "id"
      if (optionalCookie.isEmpty()) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = userService.findByLoginPass(login, password);
          if (user != null) {
            response.addCookie(new Cookie(USER_PARAM_ID, String.valueOf(user.getUserId())));
            chain.doFilter(request, response);
          } else {
            templateEngine.render("login.ftl", new HashMap<>(), response);
          }
      } else {
        chain.doFilter(request, response);
      }
    }

  }
}