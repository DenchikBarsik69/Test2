<%--
  Created by IntelliJ IDEA.
  User: alexeygoddevich
  Date: 11.02.2024
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Array Input</title>
</head>
<body>
<form action="http://localhost:8080/LR4-1.0-SNAPSHOT/hello-servlet" method="post">
    Введите числа через запятую:
    <input type="text" name="integerArray" required>
    <br>
    <input type="submit" value="ОТПРАВИТЬ">
</form>
</body>
</html>
