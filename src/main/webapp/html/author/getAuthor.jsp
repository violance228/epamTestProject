<%--
  Created by IntelliJ IDEA.
  User: violence
  Date: 23.01.2019
  Time: 11:28
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
<table class="table">
    <tbody>
        <tr>
            <td>Login</td>
            <td>${author.id}</td>
        </tr>
        <tr>
            <td>Name</td>
            <td>${author.name}</td>
        </tr>
        <tr>
            <td>Surname</td>
            <td>${author.surname}</td>
        </tr>
        <tr>
            <td>Email</td>
            <td>${author.country}</td>
        </tr>
    </tbody>
</table>
<table class="table">
    <thead class="thead-dark">
        <td>Book</td>
        <td>Name</td>
        <td>Size</td>
        <td>Language</td>
    </thead>
    <tbody>
        <c:forEach items="${author.books}" var="book">
            <tr>
                <td>${book.id}</td>
                <td>${book.name}</td>
                <td>${book.size}</td>
                <td>${book.lang}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>
