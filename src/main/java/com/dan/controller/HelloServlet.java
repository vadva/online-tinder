package com.dan.controller;


import com.dan.Enteties.Message;
import com.dan.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class HelloServlet extends HttpServlet {
    private TemplateEngine templateEngine;
    private MessageService messageService;

    public HelloServlet(TemplateEngine templateEngine, MessageService messageService) {
        this.templateEngine = templateEngine;
        this.messageService = messageService;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Message> allMessages = messageService.getAllMessages();

        HashMap<String, Object> data = new HashMap<>(1);
        data.put("message", allMessages.get(0).getMessage_text());

        templateEngine.render("hello.ftl", data, resp);
        //req.getRequestDispatcher("/hello.ftl").forward(req, resp);
    }
}
