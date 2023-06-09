package com.example;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user.view")
public class UserView extends HttpServlet {
    private String htmlTemplate = 
    "<!DOCTYPE html>"
    + "<html>"
    + "  <head>"
    + "    <meta charset='UTF-8'>"
    + "    <title>會員專區</title>"
    + "  </head>"
    + "  <body>"
    + "    <h1>%s 已登入</h1>"
    + "    <a href ='index.html'>回首頁</a>"
    + "  </body>"
    + "</html>";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String html = String.format(htmlTemplate, request.getAttribute("username"));
        response.getWriter().print(html);
    }
}
