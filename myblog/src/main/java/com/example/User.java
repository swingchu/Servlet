package com.example;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user")
public class User extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            Optional<Cookie> userCookie = Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals("username"))
                .findFirst();

            if (userCookie.isPresent()) {
                String username = userCookie.get().getValue();
                request.setAttribute("username", username);
                request.getRequestDispatcher("user.view").forward(request, response);
            } else {
                response.sendRedirect("index.html");
            }
    }
}
