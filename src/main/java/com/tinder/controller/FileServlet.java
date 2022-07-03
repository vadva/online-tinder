package com.tinder.controller;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (ServletOutputStream os = response.getOutputStream()){
            String requestName = request.getRequestURI();
            ClassLoader classLoader = this.getClass().getClassLoader();

            byte[] bytes = classLoader.getResourceAsStream(requestName.substring(1)).readAllBytes();
            os.write(bytes);
        }catch (NullPointerException npe) {
            response.setStatus(404);
        }
    }
}
