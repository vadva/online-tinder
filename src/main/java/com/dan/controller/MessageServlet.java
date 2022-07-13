package com.dan.controller;

import com.dan.Enteties.Message;
import com.dan.Enteties.User;
import com.dan.service.CookieUtil;
import com.dan.service.MessageService;
import com.dan.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static java.awt.SystemColor.window;

public class MessageServlet extends HttpServlet {
    private TemplateEngine templateEngine;
    private MessageService messageService;
    private UserService userService;

    public MessageServlet(TemplateEngine templateEngine, MessageService messageService, UserService userService) {
        this.templateEngine = templateEngine;
        this.messageService = messageService;
        this.userService = userService;
    }

    private void doRender(HttpServletResponse resp, int idUserWhoWrite, User userWhoWrite, int idUserWhomWrite) {
        User userWhomWrite = userService.read((long) idUserWhomWrite);

        List<Message> messages = messageService.getAllMessagesToUserId(idUserWhoWrite, idUserWhomWrite);
//        System.out.println(messages);

        HashMap<String, Object> data = new HashMap<>();
        data.put("messages", messages);
        data.put("userWhoWrite", userWhoWrite);
        data.put("userWhomWrite", userWhomWrite);

        templateEngine.render("/chat.ftl", data, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<Cookie> optionalCookie = CookieUtil.getCookieByName(req, "id");
        int idUserWhoWrite = Integer.parseInt(optionalCookie.get().getValue());

        User userWhoWrite = userService.read((long) idUserWhoWrite);

        String pathInfo = req.getPathInfo();
        int idUserWhomWrite = Integer.parseInt(pathInfo.replaceFirst("/", ""));
        doRender(resp, idUserWhoWrite, userWhoWrite, idUserWhomWrite);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<Cookie> optionalCookie = CookieUtil.getCookieByName(req, "id");
        int idUserWhoWrite = Integer.parseInt(optionalCookie.get().getValue());

        String pathInfo = req.getPathInfo();
        int idUserWhomWrite = Integer.parseInt(pathInfo.replaceFirst("/", ""));

        String text = req.getParameter("text");

        LocalDate localDate = LocalDate.now();

        Message message = new Message(idUserWhoWrite, idUserWhomWrite, text, localDate);
        messageService.createMessage(message);

        User userWhoWrite = userService.read((long) idUserWhoWrite);
        doRender(resp, idUserWhoWrite, userWhoWrite, idUserWhomWrite);
    }
}
