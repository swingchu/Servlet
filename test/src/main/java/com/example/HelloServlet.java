package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charaset=UTF-8");
        String name = request.getParameter("name");

        PrintWriter out = response.getWriter();
        out.print("<h1>");
        out.printf("Hello %s", name);
        out.print("</h1>");
        request.setAttribute("HelloServlet", out);
    }
}