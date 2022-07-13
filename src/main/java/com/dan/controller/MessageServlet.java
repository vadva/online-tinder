package com.dan.controller;

import com.dan.Enteties.Message;
import com.dan.Enteties.User;
import com.dan.dao.MessageDao;
import com.dan.service.CookieUtil;
import com.dan.service.MessageService;
import com.dan.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;


import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class MessageServlet extends HttpServlet {
    private TemplateEngine templateEngine;
    private MessageService messageService;
    private UserService userService;

    public MessageServlet(TemplateEngine templateEngine, MessageService messageService, UserService userService) {
        this.templateEngine = templateEngine;
        this.messageService = messageService;
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        String text = req.getParameter("text");

        Optional<Cookie> optionalCookie = CookieUtil.getCookieByName(req, "id");
        int idUserWhoWrite = Integer.parseInt(optionalCookie.get().getValue());

        User userWhoWrite = userService.read((long) idUserWhoWrite);
        System.out.println(userWhoWrite);

        String pathInfo = req.getPathInfo();
        int idUserWhomWrite = Integer.parseInt(pathInfo.replaceFirst("/", ""));

        System.out.println(idUserWhoWrite);
        System.out.println(idUserWhomWrite);

        if (text != null) {
            LocalDate localDate = LocalDate.now();
            Message message = new Message(idUserWhoWrite, idUserWhomWrite, text, localDate);
            messageService.createMessage(message);
        }

        List<Message> messages = messageService.getAllMessagesToUserId(idUserWhoWrite, idUserWhomWrite);

        HashMap<String, Object> data = new HashMap<>();
        data.put("messages", messages);
        data.put("userWhoWrite", userWhoWrite);

        templateEngine.render("/chat.ftl", data, resp);
    }


}
