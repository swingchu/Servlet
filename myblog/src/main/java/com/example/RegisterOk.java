package com.example;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register_ok.view")
public class RegisterOk extends HttpServlet {
    private String htmlTemplate = 
    "<!DOCTYPE html>"
    + "<html>"
    + "  <head>"
    + "    <meta charset='UTF-8'>"
    + "    <title>註冊成功</title>"
    + "  </head>"
    + "  <body>"
    + "    <h1>註冊成功</h1>"
    + "    <a href ='index.html'>回首頁</a>"
    + "  </body>"
    + "</html>";

    protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(htmlTemplate);
    }
}
