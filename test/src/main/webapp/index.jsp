<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="utf-8">
    </head>
    <body>
        <h2>getInitParameter test : </h2>
        <%= getServletContext().getInitParameter("welcome_msg") %>
        <br>
        Hi <%= getServletConfig().getInitParameter("defaultGuestName") %>
        <hr>

        <h2>doGetTest</h2>
        <a href="HelloServlet">Hello Servlet</a><br>
        <a href="doGetTest?first_name=swing&last_name=chu">doGet Test</a>
        <form action="doGetTest" method="GET">
            名字 : <input type="text" name="first_name"> <br>
            姓氏 : <input type="text" name="last_name"> <br>
            <input type="submit" value="doGet Form Test">
        </form>

        <form action="doGetTest" method="POST">
            你的興趣是 : <br>
            <input type="checkbox" name="habbit" value="運動"/>運動 <br>
            <input type="checkbox" name="habbit" value="音樂"/>音樂 <br>
            <input type="checkbox" name="habbit" value="繪畫"/>繪畫 <br>
            <input type="checkbox" name="habbit" value="閱讀"/>閱讀 <br>
            <input type="checkbox" name="habbit" value="電影"/>電影 <br>
            <input type="submit" value="doPost Form Test">
        </form>

    </body>
</html>
