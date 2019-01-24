<%--
  Created by IntelliJ IDEA.
  User: violence
  Date: 23.01.2019
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <td>Book</td>
            <td>${book.id}</td>
        </tr>
        <tr>
            <td>Name</td>
            <td>${book.name}</td>
        </tr>
        <tr>
            <td>Size</td>
            <td>${book.size}</td>
        </tr>
        <tr>
            <td>Language</td>
            <td>${book.lang}</td>
        </tr>
        <tr>
            <td>Edit</td>
            <td>${book.lang}</td>
        </tr>
    </tbody>
</table>
<table class="table">
        <thead class="thead-dark">
            <td>Author</td>
            <td>Name</td>
            <td>Surname</td>
            <td>County</td>
        </thead>
        <tbody>
            <c:forEach items="${book.authors}" var="author">
                <tr>
                    <td>${author.id}</td>
                    <td>${author.name}</td>
                    <td>${author.surname}</td>
                    <td>${author.country}</td>
                </tr>
            </c:forEach>
        </tbody>
</table>
</body>
</html>
