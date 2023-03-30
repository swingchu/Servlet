package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class doGetTest extends HttpServlet{
    private String message;

    public void init() throws ServletException{
        super.init();
        message = "Hello World";
    }
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.service(request,response);
    }
    public void destroy() {
        super.destroy();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws IOException, ServletException
    {
        response.setContentType("text/html;charset=UTF-8");
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");

        PrintWriter out = response.getWriter();
        out.println("<b>doGet test : " + first_name + " " + last_name + "</b>");

    }    

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            String first_name = request.getParameter("first_name");
            String last_name = request.getParameter("last_name");

            PrintWriter out = response.getWriter();
            out.println("<b>doPost test : " + first_name + " " + last_name + " </br>");
    }

}
