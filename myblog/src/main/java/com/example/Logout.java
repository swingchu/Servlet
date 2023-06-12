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

@WebServlet("/logout")
public class Logout extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            Optional<Cookie> userCookie = Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals("username"))
                .findFirst();

            if (userCookie.isPresent()) {
                Cookie cookie = new Cookie("username", "");
                cookie.setMaxAge(0); // Set the maximum age of the cookie to 0, making it immediately invalid
                response.addCookie(cookie);
            }
            
            response.sendRedirect("index.html");
    }
}
