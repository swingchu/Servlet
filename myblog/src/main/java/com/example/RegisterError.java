package com.example;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register_error.view")
public class RegisterError extends HttpServlet {
    private String htmlTemplate = 
    "<!DOCTYPE html>"
    + "<html>"
    + "  <head>"
    + "    <meta charset='UTF-8'>"
    + "    <title>註冊資料錯誤</title>"
    + "  </head>"
    + "  <body>"
    + "    <h1>註冊資料錯誤</h1>"
    + "    <ul style='color: rgb(255, 0, 0)'>"
    + "      %s"
    + "    </ul>"
    + "    <a href ='register.html'>回上一頁</a>"
    + "  </body>"
    + "</html>";

    protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");

        List<String> errors = ((List<String>) request.getAttribute("errors"));
        String htmlmsg="";
        for (String error : errors) {
            htmlmsg = htmlmsg + "<li>" + error + "</li>";
        }

        String html = String.format(htmlTemplate, htmlmsg);
        response.getWriter().print(html);
    }
}
