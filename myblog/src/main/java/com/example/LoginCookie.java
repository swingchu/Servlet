package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

@WebServlet("/logincookie")
public class LoginCookie extends HttpServlet{
    private final String USERS = "D:/_Documents/code/java/servlet/myblog/users";
    private final String OK_PATH = "user.view";
    private final String ERROR_PATH = "index.html";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String page = login(username, password)? OK_PATH : ERROR_PATH;
        if (page == OK_PATH) {
            processCookie(request, response);
        }
        response.sendRedirect(page);
    }

    private boolean login(String username, String password)
            throws IOException {

        if (username != null && username.trim().length() != 0
            && password != null ){
            var home = Paths.get(USERS, username);
            return Files.exists(home) && isCorrectPassword(password, home);
        }
        return false;
    }

    private boolean isCorrectPassword(String password, Path home)
            throws IOException {
        Path profile = home.resolve("profile");
        try ( var reader = Files.newBufferedReader(profile) ) {
            var data = reader.readLine().split("\t");
            var encrypt = Integer.parseInt(data[1]);
            var salt = Integer.parseInt(data[2]);
            return password.hashCode() + salt == encrypt;

        }
    }

    private void processCookie(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
            Cookie c = new Cookie("username", request.getParameter("username"));
            c.setMaxAge(60*60*24*7);// 7 days
            response.addCookie(c);
    }

}

