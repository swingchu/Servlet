package com.example;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/helloView")
public class TestView extends HttpServlet{
    private String htmlTemplate = 
        "<!DOCTYPE html>"
      + "<html>"
      + "  <head>"
      + "    <meta charset='UTF-8'>"
      + "    <title>%s</title>"
      + "  </head>"
      + "  <body>"
      + "    <h1>%s</h1>"
      + "    <a href='/testmvc'>back</a>"
      + "  </body>"
      + "</html>";

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) 
                    throws ServletException, IOException {
        String user = request.getParameter("user");
        String message = (String) request.getAttribute("message");
        String html = String.format(htmlTemplate, user, message);
        response.getWriter().print(html);
    }    
}
