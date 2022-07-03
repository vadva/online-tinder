package com.tinder;

import com.tinder.controller.FileServlet;
import com.tinder.controller.LikesServlet;
import com.tinder.controller.LoginServlet;
import com.tinder.controller.TemplateEngine;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;


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
        TemplateEngine templateEngine = new TemplateEngine();

        handler.addServlet(new ServletHolder(new FileServlet()), "/assets/*");
        handler.addServlet(new ServletHolder(new LoginServlet(templateEngine)), "/");
        handler.addServlet(new ServletHolder(new LikesServlet(templateEngine)),"/liked");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
