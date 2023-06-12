package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class Register extends HttpServlet {
    private final String USERS = "D:/study/java/code/servlet/myblog/users";
    private final String OK_PATH = "user";
    private final String ERROR_PATH = "register_error.view";

    private final Pattern emailRegex = Pattern.compile(
        "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$");
    private final Pattern passwdRegex = Pattern.compile("^\\w{8,16}$");
    private final Pattern usernameRegex = Pattern.compile("^\\w{1,16}$");

    protected void doPost(
        HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        var email = request.getParameter("email");
        var username = request.getParameter("username");
        var password = request.getParameter("password");
        var password2 = request.getParameter("password2");
        // var code = request.getParameter("code");
        // String code2 = (String) request.getAttribute("code");

        var errors = new ArrayList<String>();
        if (!validateEmail(email)) {
            errors.add("未填寫電子郵件或格式不正確");
        }
        if (!validateUsername(username)) {
            errors.add("未填寫使用者名稱或格式不正確");
        }
        if (!validatePassword(password, password2)) {
            errors.add("請確認密碼符合格式且輸入正確");
        }
        // if (!validateCode(code, code2)) {
        //     errors.add("驗證碼錯誤 : " + code + " != " + code2);
        // }

        String path;
        if (errors.isEmpty()) {
            if (tryCreateUser(email, username, password)) {
                path = OK_PATH;
            } else {
                path = ERROR_PATH;
                errors.add("使用者名稱已存在");
                request.setAttribute("errors", errors);
            }
        } else {
            path = ERROR_PATH;
            request.setAttribute("errors", errors);
        }

        request.getRequestDispatcher(path).forward(request, response);
    }

    private boolean validateEmail(String email) {
        return email != null && emailRegex.matcher(email).find();
    }

    private boolean validateUsername(String username) {
        return username != null & usernameRegex.matcher(username).find();
    }

    private boolean validatePassword(String password, String password2) {
        return password != null &&
                passwdRegex.matcher(password).find() &&
                password.equals(password2);
    }

    // private boolean validateCode(String code, String code2){
    //     return code != null && code.equals(code2);
    // }

    private boolean tryCreateUser (
        String email, String username, String password) throws IOException {
        
        //check if folder USERS/username exists
        Path home = Paths.get(USERS, username);    
        if (!Files.exists(home) && !Files.isDirectory(home)) {
            log("Creating " + home);
            createUser(home, email, password);
            return true;
        }
        else {
            log(home + " already exists");
            return false;
        }
    }

    private void createUser(Path home, String email, String password) 
        throws IOException {

        Files.createDirectories(home);
        var salt = ThreadLocalRandom.current().nextInt();
        var encrypt = String.valueOf(salt + password.hashCode());
        var profile = home.resolve("profile");
        try (var writer = Files.newBufferedWriter(profile)) {
            writer.write(String.format("%s\t%s\t%d", email, encrypt, salt));
        }
    }
}
