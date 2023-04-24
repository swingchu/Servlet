package com.example;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/helloControl")
public class TestControl extends HttpServlet{
    private TestModel model = new TestModel();
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
        throws ServletException, IOException {
            String name = request.getParameter("user");
            String message = model.doHello(name);
            request.setAttribute("message", message);
            request.getRequestDispatcher("helloView").forward(request, response);
        }
}
