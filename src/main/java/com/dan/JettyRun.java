package com.dan;

import com.dan.controller.*;
import com.dan.dao.*;
import com.dan.service.*;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.util.EnumSet;


public class JettyRun {

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
        UserService userService = new UserServiceImpl(new UserJdbcDao());
        MessageDao messageDao=new MessageJdbcDao();
        MessageService messageService = new MessageServiceImpl(messageDao);

        final LikesDao likesDao = new LikesJdbcDao();
        LikeService likeService = new LikeServiceImpl(likesDao);

        TemplateEngine templateEngine = new TemplateEngine();

        handler.addFilter(new FilterHolder(new LoginFilter(templateEngine, userService)), "/*", EnumSet.of(DispatcherType.REQUEST));

        handler.addServlet(new ServletHolder(new FileServlet()), "/assets/*");
        handler.addServlet(new ServletHolder(new TinderWelcomeServlet(templateEngine)), "/");
        handler.addServlet(new ServletHolder(new TinderWelcomeServlet(templateEngine)), "/tinder");
        handler.addServlet(new ServletHolder(new LoginServlet(templateEngine)), "/login");
        handler.addServlet(new ServletHolder(new LogoutServlet(userService)), "/logout");
        handler.addServlet(new ServletHolder(new ProfilesServlet(userService, likeService, templateEngine)), "/profiles");
        handler.addServlet(new ServletHolder(new RegistrationServlet(userService, templateEngine)), "/create");
        handler.addServlet(new ServletHolder(new LikesServlet(likeService, templateEngine)),"/liked");
        handler.addServlet(new ServletHolder(new MessageServlet(templateEngine,messageService,userService)),"/message/*");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
