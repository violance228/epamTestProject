<%--
  Created by IntelliJ IDEA.
  User: violence
  Date: 15.01.2019
  Time: 11:07
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
            <td>Catalog</td>
            <td>${catalog.id}</td>
        </tr>
        <tr>
            <td>User</td>
            <td>${catalog.user.name} ${catalog.user.surname}</td>
        </tr>
        <tr>
            <td>Book</td>
            <td>${catalog.book.name}</td>
        </tr>
        <tr>
            <td>Date from</td>
            <td>${catalog.dateFrom}</td>
        </tr>
        <tr>
            <td>Date to</td>
            <td>${catalog.dateTo}</td>
        </tr>
    </tbody>
</table>
</body>
</html>
