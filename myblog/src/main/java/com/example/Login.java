package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class Login extends HttpServlet{
    private final String USERS = "D:/_Documents/code/java/servlet/myblog/users";
    private final String OK_PATH = "user";
    private final String ERROR_PATH = "login_error.view";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        var username = request.getParameter("username");
        var password = request.getParameter("password");
        var auto = request.getParameter("auto");
        var errors = new ArrayList<String>();
        if (username == null || username.trim().length() == 0) {
            errors.add("未填寫使用者名稱");
        }

        if (login(username, password)) {
            Cookie cookie = new Cookie("username", username);
            if (auto != null) {
                cookie.setMaxAge( 60*60*7*24 ); // 7 days
            }
            response.addCookie(cookie);
            response.sendRedirect(OK_PATH);
        }
        else {
            errors.add("登入失敗，請檢查帳號密碼");
            request.setAttribute("errors", errors);
            request.getRequestDispatcher(ERROR_PATH).forward(request, response);
        }
        
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
}

