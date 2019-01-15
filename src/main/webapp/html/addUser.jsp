<%--
  Created by IntelliJ IDEA.
  User: s3809
  Date: 1/14/2019
  Time: 8:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Sign-Up</title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<form action="/SecurityExample_war/registration" method="post">
    <table>
        <tr>
            <td>Login</td>
            <td><input type="text" name="login"/></td>
        </tr>
        <tr>
            <td>Name</td>
            <td><input type="text" name="name"/></td>
        </tr>
        <tr>
            <td>Surname</td>
            <td><input type="text" name="surname"/></td>
        </tr>
        <tr>
            <td>Mobile</td>
            <td><input type="text" name="phone"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="email" name="email"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Regs"/></td>
        </tr>
    </table>
</form>
</body>
</html>
