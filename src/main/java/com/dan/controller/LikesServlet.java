package com.dan.controller;

import com.dan.Enteties.User;
import com.dan.service.CookieUtil;
import com.dan.service.LikeService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class LikesServlet extends HttpServlet {
    LikeService likeService;
    TemplateEngine templateEngine;


    public LikesServlet(LikeService likeService, TemplateEngine templateEngine) {
        this.likeService = likeService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> data = new HashMap<>();
        Optional<Cookie> optionalCookie = CookieUtil.getCookieByName(req, "id");

        int loggedUserId = Integer.parseInt(optionalCookie.get().getValue());
        List<User> likedUsers = likeService.readLikedUsers(loggedUserId);

        data.put("likedUsers", likedUsers);
        templateEngine.render("like-page.ftl", data, resp);
    }
}
