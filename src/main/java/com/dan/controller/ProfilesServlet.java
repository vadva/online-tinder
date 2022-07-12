package com.dan.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import com.dan.service.LikeService;
import com.dan.service.UserService;

public class ProfilesServlet extends HttpServlet {
    private UserService userService;
    private LikeService likeService;
    private TemplateEngine templateEngine;

    public ProfilesServlet(UserService userService, LikeService likeService, TemplateEngine templateEngine) {
        this.userService = userService;
        this.likeService = likeService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long activeUserid = (Long) req.getAttribute("id");

        int candidateId;

        String dislike = req.getParameter("Dislike");
        String like = req.getParameter("Like");

        if (dislike != null) {
            candidateId = Integer.parseInt(dislike.substring(dislike.length() - 1));
            likeService.likeUser(Math.toIntExact(activeUserid), candidateId, false);
        } else if (like != null) {
            candidateId = Integer.parseInt(like.substring(like.length() - 1));
            likeService.likeUser(Math.toIntExact(activeUserid), candidateId, true);
        }

        renderUsers(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        renderUsers(req, resp);
    }


    public void renderUsers (HttpServletRequest req, HttpServletResponse resp) {
        Long activeUserid = (Long) req.getAttribute("id");
        HashMap<String, Object> data = new HashMap<>(1);
        data.put("users", userService.findNewProfiles(activeUserid));
        templateEngine.render("new-profiles.ftl", data, resp);
    }
}