<%--
  Created by IntelliJ IDEA.
  User: violence
  Date: 23.01.2019
  Time: 11:29
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
    <thead class="thead-dark">
        <td>Author</td>
        <td>Name</td>
        <td>Surname</td>
        <td>County</td>
    </thead>
    <tbody>
        <c:forEach items="${authors}" var="author">
            <tr>
                <td><a href="/getAuthor?author_id=${author.id}">${author.id}</a></td>
                <td>${author.name}</td>
                <td>${author.surname}</td>
                <td>${author.country}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>
