package com.dan.controller;

import com.dan.Enteties.Message;
import com.dan.dao.MessageDao;
import com.dan.service.MessageService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

public class MessageServlet extends HttpServlet {
    private TemplateEngine templateEngine;
    private MessageDao messageService;

    public MessageServlet(TemplateEngine templateEngine, MessageService messageService){
        this.templateEngine=templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        String idUser = req.getParameter("idUser");
        System.out.println(idUser);

        List<Message> messages = messageService.getAllMessages();

        HashMap<String,Object> data= new HashMap<>();
        data.put("messages",messages);
        templateEngine.render("/chat.ftl",data,resp);
    }



}
