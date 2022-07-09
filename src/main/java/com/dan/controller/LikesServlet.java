package com.dan.controller;

import com.dan.Enteties.User;
import com.dan.dao.LikesDao;
import com.dan.dao.LikesJdbcDao;
import com.dan.service.LikeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class LikesServlet extends HttpServlet {
    TemplateEngine templateEngine;
    LikeService likeService;


    public LikesServlet(TemplateEngine templateEngine, LikeService likeService) {
        this.templateEngine = templateEngine;
        this.likeService = likeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> data = new HashMap<>();
//        HttpSession session = req.getSession();
//        User loggedUser = (User) session.getAttribute("user");
//        List<User> likedUsers = likeService.readLikedUsers(loggedUser.getId());

//        data.put("likedUsers", likedUsers);
//        data.put("user", loggedUser);
        data.put("like", "likes");

        templateEngine.render("people-list.ftl", data, resp);
    }
}
