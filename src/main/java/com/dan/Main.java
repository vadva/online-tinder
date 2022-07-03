package com.dan;

import com.dan.controller.HelloServlet;
import com.dan.controller.TemplateEngine;
import com.dan.dao.MessageDao;
import com.dan.dao.MessageJdbcDao;
import com.dan.service.MessageService;
import com.dan.service.MessageServiceImpl;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;


public class Main {

    public static void main(String[] args) throws Exception {
        String portStr = System.getenv("PORT");
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        String username = System.getenv("JDBC_DATABASE_USERNAME");
        String password = System.getenv("JDBC_DATABASE_PASSWORD");
        portStr = portStr == null ? "8088" : portStr;
        Integer port = Integer.parseInt(portStr);
        System.out.println("PORT: " + port);
        Server server = new Server(port);
        ServletContextHandler handler = new ServletContextHandler();
        final MessageDao userDao = new MessageJdbcDao();
        MessageService userService = new MessageServiceImpl(userDao);
        TemplateEngine templateEngine = new TemplateEngine();

//        SessionHandler sessionHandler = new SessionHandler();
//        handler.setSessionHandler(sessionHandler);


        handler.addServlet(new ServletHolder(new HelloServlet(templateEngine,userService)), "/hello");
//        handler.addServlet(new ServletHolder(new LoginServlet(userDao, templateEngine)), "/");
//        handler.addServlet(new ServletHolder(new FileServlet()), "/assets/*");
//        handler.addFilter(new FilterHolder(new LoginFilter(templateEngine, userDao)), "/*", EnumSet.of(DispatcherType.REQUEST));
//        handler.addServlet(new ServletHolder(new RegistrationServlet(userDao, templateEngine)), "/registration");

//        handler.addServlet(new ServletHolder(new UserServlet(userDao)), "/users");

//        handler.addServlet(new ServletHolder(new LikeServlet(userDao)), "/liked");

//        handler.addServlet(new ServletHolder(new MessageServlet(messageDao, userDao)), "/messages/*");
//        handler.addFilter(MessageFilter.class, "/messages/*", EnumSet.of(DispatcherType.REQUEST));


//        handler.addServlet(RedirectServlet.class, "/*");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
