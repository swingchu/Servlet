<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="utf-8">
    </head>
    <body>
        <h2>Hello World!</h2>
        <b> Hello! <%= config.getInitParameter("defaultGuestName") %>! </b>
        <a href="HelloServlet">Hello Servlet</a><br>
        <a href="doGetTest?first_name=swing&last_name=chu">doGet Test</a>
        <form action="doGetTest" method="GET">
            名字 : <input type="text" name="first_name"> <br>
            姓氏 : <input type="text" name="last_name"> <br>
            <input type="submit" value="送出">
        </form>
    </body>
</html>
