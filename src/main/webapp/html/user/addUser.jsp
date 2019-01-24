<%--
  Created by IntelliJ IDEA.
  User: s3809
  Date: 1/14/2019
  Time: 8:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" pageEncoding="UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="../fragments/header.jspf"%>
</head>
<body>
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <%@include file="../fragments/navbar.jspf"%>
    </nav>
    <form action="/registration" method="post">
        <table class="table">
            <tbody>
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
            </tbody>
        </table>
    </form>
</body>
</html>
