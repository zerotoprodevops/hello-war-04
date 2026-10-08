package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@WebServlet("/")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Hello</title></head><body>");
        out.println("<h1>Hello from Tomcat!</h1>");
        out.println("<p>Hostname: " + java.net.InetAddress.getLocalHost().getHostName() + "</p>");
        out.println("<p>Time: " + LocalDateTime.now() + "</p>");
        out.println("<p>Servlet: HelloServlet</p>");
        out.println("</body></html>");
    }
}
