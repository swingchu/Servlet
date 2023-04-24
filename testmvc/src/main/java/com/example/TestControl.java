package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/testmvc")
public class TestControl extends HttpServlet{
    private TestModel model = new TestModel();
    @Override
    protested void doGet(HttpServletRequest request,
                         HttpServletResponse response)
        throws ServletException, IOException {
            String name = request.getParameter("user");
            String message = model.doHello(name);
            request.setAttribute("message", message);
            request.getRequestDispatcher("testmvc.view").forward(request, response);
        }
}